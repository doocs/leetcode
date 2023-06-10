# Write your MySQL query statement below
(
    select
        name results
    from
        MovieRating r
        left join Users u on u.user_id = r.user_id
    group by
        r.user_id
    order by
        count(1) desc,
        name
    limit
        1
)
union
all (
    select
        title results
    from
        MovieRating r
        left join Movies m on m.movie_id = r.movie_id
    where
        date_format(created_at, '%Y-%m') = '2020-02'
    group by
        m.movie_id
    order by
        avg(rating) desc,
        title
    limit
        1
);