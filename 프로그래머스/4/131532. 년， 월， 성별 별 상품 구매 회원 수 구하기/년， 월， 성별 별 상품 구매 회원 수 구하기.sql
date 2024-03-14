SELECT TO_CHAR(B.SALES_DATE, 'YYYY') AS YEAR
    , TO_NUMBER(TO_CHAR(B.SALES_DATE, 'MM')) AS MONTH
    , A.GENDER
    , COUNT(DISTINCT A.USER_ID) AS USERS
FROM USER_INFO A
    , ONLINE_SALE B
WHERE 1=1
    AND B.USER_ID = A.USER_ID
    AND A.GENDER IS NOT NULL
GROUP BY TO_CHAR(B.SALES_DATE, 'YYYY')
    , TO_NUMBER(TO_CHAR(B.SALES_DATE, 'MM'))
    , A.GENDER
ORDER BY 1, 2, 3
;