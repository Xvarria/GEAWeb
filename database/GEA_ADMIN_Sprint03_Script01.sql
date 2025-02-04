DROP TABLE CALENDARIO;
CREATE TABLE CALENDARIO (
	DIA_NUMERO INTEGER(6) NOT NULL,
    FECHA DATE,
    PRIMARY KEY (DIA_NUMERO)
);

DROP PROCEDURE GEA_WEB.AVANZAR_CALENDARIO;
/**
Autor: Marcos Chavarria Fallas
Fecha: 09/06/2019
Proceso para llenar la tabla calendario hasta el día indicado en el paráemtro
*/
DELIMITER //
CREATE PROCEDURE GEA_WEB.AVANZAR_CALENDARIO(IN P_FECHA_FINAL DATE)
BEGIN
    /*CONSTANTES*/
    DECLARE C_FECHA_INICIO DATE DEFAULT STR_TO_DATE(20190511,'%Y%m%d');
    /*VARIABLES*/
	DECLARE V_CURRENT_DATE DATE;
	/*CRUSORES*/
    /*Definicion de Handlers*/
    /*iNICIO*/
    SELECT "INICIO SP";
	SELECT IFNULL(MAX(FECHA),C_FECHA_INICIO) INTO V_CURRENT_DATE FROM CALENDARIO;
    SELECT V_CURRENT_DATE;
    SELECT P_FECHA_FINAL;
    SELECT V_CURRENT_DATE < P_FECHA_FINAL;
    WHILE(V_CURRENT_DATE < P_FECHA_FINAL) DO
		SELECT DATE_ADD(V_CURRENT_DATE, INTERVAL 1 DAY) INTO V_CURRENT_DATE;
		SELECT V_CURRENT_DATE as to_INSERT;
        INSERT INTO CALENDARIO(DIA_NUMERO, FECHA) VALUES (DATE_FORMAT(V_CURRENT_DATE, '%Y%m%d'),V_CURRENT_DATE);
    END WHILE;
END //
DELIMITER ;	

CAll AVANZAR_CALENDARIO ('20190831');

COMMIT;
DROP FUNCTION GEA_WEB.GET_MAX_VOLUMEN;
/**
*Función que retorna la máxima lectura registrada hasta el día anterior al indicado por el parámetro, para un medidod específico
*/
DELIMITER //
CREATE FUNCTION GEA_WEB.GET_MAX_VOLUMEN(P_MEDIDOR_ID INTEGER, P_DIA_NUMERO INTEGER) RETURNS FLOAT(18,8) BEGIN
	DECLARE V_RET_VAL FLOAT(18,8);
	SELECT IFNULL(MAX(VOLUMEN),0) INTO V_RET_VAL FROM LECTURA WHERE MEDIDOR_ID=P_MEDIDOR_ID AND DIA_NUMERO < P_DIA_NUMERO;
    RETURN V_RET_VAL;
END //
DELIMITER ;	

DROP FUNCTION GEA_WEB.GET_ULTIMA_FECHA_CONSOLIDADA;
/**
*Función que retorna la máxima lectura registrada hasta el día anterior al indicado por el parámetro, para un medidod específico
*/
DELIMITER //
CREATE FUNCTION GEA_WEB.GET_ULTIMA_FECHA_CONSOLIDADA(P_MEDIDOR_ID INTEGER) RETURNS INTEGER BEGIN
	DECLARE V_RET_VAL DATE;
    -- Agregar proceso para Revisar máxima fecha del consolidado
	SELECT IFNULL(MAX(DIA_NUMERO), (SELECT DATE_FORMAT(FECHA_INCLUSION, 'Y%m%d%') FROM MEDIDOR WHERE MEDIDOR_ID = P_MEDIDOR_ID)) INTO V_RET_VAL FROM LECTURA WHERE MEDIDOR_ID=P_MEDIDOR_ID AND DIA_NUMERO < P_DIA_NUMERO;
    
    RETURN V_RET_VAL;
END //
DELIMITER ;	


/*CREA LA VISTA PARA EL CALENDARIO, DESDE LA ULTIMA FECHA CONSOLIDAD HASTA EL DÍA DE AYER*/
DROP VIEW V_CALENDARIO_UF;
CREATE VIEW V_CALENDARIO_UF AS
SELECT * FROM CALENDARIO WHERE DIA_NUMERO >= DATE_FORMAT('2019-05-11', '%Y%m%d') AND DIA_NUMERO <= DATE_FORMAT(DATE_ADD(SYSDATE(), INTERVAL -1 DAY),'%Y%m%d');

DROP VIEW V_CALENDARIO_UF_MEDIDOR;
CREATE VIEW V_CALENDARIO_UF_MEDIDOR AS
SELECT C.*, M.MEDIDOR_ID FROM V_CALENDARIO_UF C JOIN MEDIDOR M WHERE C.DIA_NUMERO >= DATE_FORMAT(DATE(M.FECHA_INCLUSION),'%Y%m%d');

-- View para consulta diaria de la fecha actual y hasta 60 días atrás
DROP VIEW V_LECUTURA_AGRUPACION_DIARIA;
CREATE VIEW V_LECUTURA_AGRUPACION_DIARIA AS
SELECT MEDIDOR_ID, DIA_NUMERO, MAX(VOLUMEN) MAX_VOLUMEN, COUNT(LECTURA_ID) CANTIDAD_LECTURA FROM LECTURA WHERE PROCESADA < 2 GROUP BY MEDIDOR_ID, DIA_NUMERO;

-- View para el proceso de consolidación
DROP VIEW V_LECUTURA_AGRUPACION_DIARIA_POR_PROCESAR;
CREATE VIEW V_LECUTURA_AGRUPACION_DIARIA_POR_PROCESAR AS
SELECT MEDIDOR_ID, DIA_NUMERO, MAX(VOLUMEN) MAX_VOLUMEN, COUNT(LECTURA_ID) CANTIDAD_LECTURA FROM LECTURA WHERE PROCESADA = 1 GROUP BY MEDIDOR_ID, DIA_NUMERO;

-- View para consulta diaria de la fecha actual y hasta 60 días atrás
DROP VIEW V_LECTURA_DIARIA_POR_CONSOLIDAR;
CREATE VIEW V_LECTURA_DIARIA_POR_CONSOLIDAR AS
SELECT C.MEDIDOR_ID, C.DIA_NUMERO, FECHA, CANTIDAD_LECTURA, L.MAX_VOLUMEN, GET_MAX_VOLUMEN(C.MEDIDOR_ID, C.DIA_NUMERO) MAX_VOLUMEN_CALC FROM 
V_CALENDARIO_UF_MEDIDOR C LEFT JOIN 
V_LECUTURA_AGRUPACION_DIARIA L ON (C.DIA_NUMERO = L.DIA_NUMERO AND C.MEDIDOR_ID = L.MEDIDOR_ID);

--
DROP VIEW V_LECTURA_DIARIA_POR_PROCESAR;
CREATE VIEW V_LECTURA_DIARIA_POR_PROCESAR AS
SELECT C.MEDIDOR_ID, C.DIA_NUMERO, FECHA, CANTIDAD_LECTURA, L.MAX_VOLUMEN, GET_MAX_VOLUMEN(C.MEDIDOR_ID, C.DIA_NUMERO) MAX_VOLUMEN_CALC FROM 
V_CALENDARIO_UF_MEDIDOR C LEFT JOIN 
V_LECUTURA_AGRUPACION_DIARIA_POR_PROCESAR L ON (C.DIA_NUMERO = L.DIA_NUMERO AND C.MEDIDOR_ID = L.MEDIDOR_ID);

DROP VIEW V_LECTURA_DIARIA;
CREATE VIEW V_LECUTURA_DIARIA AS
SELECT 
	MAX(LECTURA_DIARIA_ID) AS LECTURA_DIARIA_ID, 
    MEDIDOR_ID,
    AVG(TEMPERATURA) TEMPERATURA,
    MAX(MAX_LECTURA) AS MAX_LECTURA, 
    MAX(CONSUMO) AS CONSUMO, 
    MAX(CANTIDAD_LECTURA) CANTIDAD_LECTURA, 
    DIA_NUMERO FROM (
		SELECT LECTURA_DIARIA_ID, MEDIDOR_ID, TEMPERATURA, MAX_LECTURA, CONSUMO, DIA_NUMERO, CANTIDAD_LECTURA  FROM LECTURA_DIARIA
		UNION ALL
		SELECT 0 AS LECTURA_DIARIA_ID, MEDIDOR_ID, NULL TEMPERATURA, IFNULL(MAX_VOLUMEN, MAX_VOLUMEN_CALC) MAX_LECTURA,IFNULL(MAX_VOLUMEN, MAX_VOLUMEN_CALC) -  MAX_VOLUMEN_CALC AS CONSUMO , DIA_NUMERO, IFNULL(CANTIDAD_LECTURA,0) AS CANTIDAD_LECTURA
		FROM  V_LECTURA_DIARIA_POR_CONSOLIDAR
) AS TODO_LECTURA_DIARIA GROUP BY MEDIDOR_ID, DIA_NUMERO;

DROP VIEW V_LECTURA_DIARIA;
CREATE VIEW V_LECUTURA_DIARIA AS
SELECT
	MAX(LECTURA_DIARIA_ID) AS LECTURA_DIARIA_ID, 
    MEDIDOR_ID,
    AVG(TEMPERATURA) TEMPERATURA,
    MAX(MAX_LECTURA) AS MAX_LECTURA, 
    MAX(CONSUMO) AS CONSUMO, 
    MAX(CANTIDAD_LECTURA) CANTIDAD_LECTURA, 
    DIA_NUMERO FROM (
		SELECT LECTURA_DIARIA_ID, MEDIDOR_ID, TEMPERATURA, MAX_LECTURA, CONSUMO, DIA_NUMERO, CANTIDAD_LECTURA  FROM LECTURA_DIARIA
		UNION ALL
		SELECT 0 AS LECTURA_DIARIA_ID, MEDIDOR_ID, NULL TEMPERATURA, IFNULL(MAX_VOLUMEN, MAX_VOLUMEN_CALC) MAX_LECTURA,IFNULL(MAX_VOLUMEN, MAX_VOLUMEN_CALC) -  MAX_VOLUMEN_CALC AS CONSUMO , DIA_NUMERO, IFNULL(CANTIDAD_LECTURA,0) AS CANTIDAD_LECTURA
		FROM  V_LECTURA_DIARIA_POR_CONSOLIDAR
) AS TODO_LECTURA_DIARIA GROUP BY MEDIDOR_ID, DIA_NUMERO ORDER BY DIA_NUMERO;

DROP VIEW V_LECUTRA_DIARIA_PARA_INSERTAR;
CREATE VIEW V_LECTURA_DIARIA_PARA_INSERTAR AS
SELECT MEDIDOR_ID, IFNULL(MAX_VOLUMEN, MAX_VOLUMEN_CALC) MAX_LECTURA, IFNULL(MAX_VOLUMEN, MAX_VOLUMEN_CALC) -  MAX_VOLUMEN_CALC AS CONSUMO , DIA_NUMERO, IFNULL(CANTIDAD_LECTURA,0) AS CANTIDAD_LECTURA
	FROm V_LECTURA_DIARIA_POR_PROCESAR;
