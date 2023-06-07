# Write your MySQL query statement below
select
    left_operand,
    operator,
    right_operand,
    case
        when (
            (
                e.operator = '='
                and v1.value = v2.value
            )
            or (
                e.operator = '>'
                and v1.value > v2.value
            )
            or (
                e.operator = '<'
                and v1.value < v2.value
            )
        ) then 'true'
        else 'false'
    end value
from
    Expressions e
    left join Variables v1 on e.left_operand = v1.name
    left join Variables v2 on e.right_operand = v2.name