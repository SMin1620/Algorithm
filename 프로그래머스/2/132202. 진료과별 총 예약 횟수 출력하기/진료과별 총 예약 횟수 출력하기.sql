SELECT A.MCDP_CD AS "진료과코드", COUNT(*) AS "5월예약건수"
FROM APPOINTMENT A
WHERE TO_CHAR(A.APNT_YMD, 'YYYY-MM-DD') LIKE '2022-05%'
GROUP BY A.MCDP_CD
ORDER BY 2, 1
;