SELECT A.ORDER_ID
    , A.PRODUCT_ID
    , TO_CHAR(A.OUT_DATE, 'YYYY-MM-DD') AS OUT_DATE
    , CASE WHEN TO_CHAR(OUT_DATE, 'YYYY-MM-DD') <= '2022-05-01' THEN '출고완료'
        WHEN TO_CHAR(OUT_DATE, 'YYYY-MM-DD') > '2022-05-01' THEN '출고대기'
        ELSE '출고미정'
      END 출고여부
FROM FOOD_ORDER A
ORDER BY 1
;