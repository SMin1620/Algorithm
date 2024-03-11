SELECT TRUNC(A.PRICE / 10000) * 10000 AS PRICE_GROUP, COUNT(A.PRICE) AS PRODUCTS
FROM PRODUCT A
GROUP BY TRUNC(A.PRICE / 10000) * 10000
ORDER BY 1
;