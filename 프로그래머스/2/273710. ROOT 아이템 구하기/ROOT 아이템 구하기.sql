select ITEM_ID, ITEM_NAME
from ITEM_INFO
where ITEM_ID in (
    select ITEM_ID
    from ITEM_TREE
    where PARENT_ITEM_ID is NULL
)
order by ITEM_ID asc;