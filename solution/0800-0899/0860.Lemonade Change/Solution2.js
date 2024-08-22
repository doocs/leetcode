const lemonadeChange = (bills, f = 0, t = 0) =>
    bills.every(
        x => (
            (!(x ^ 5) && ++f) ||
                (!(x ^ 10) && (--f, ++t)) ||
                (!(x ^ 20) && (t ? (f--, t--) : (f -= 3), 1)),
            f >= 0
        ),
    );
