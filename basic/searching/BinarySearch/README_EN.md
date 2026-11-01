<!-- solution:start -->

# Binary Search

## Algorithm Templates

The essence of binary search is not "monotonicity", but "boundary". As long as a certain property is found that divides the entire interval into two, the boundary point can be found using binary search.

These templates assume nonnegative 32-bit `int` bounds with `left <= right`. They compute the midpoint from the difference to avoid overflowing `left + right`.

### Template 1

```java
boolean check(int x) {
}

int search(int left, int right) {
    while (left < right) {
        int mid = left + ((right - left) >> 1);
        if (check(mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

### Template 2

```java
boolean check(int x) {
}

int search(int left, int right) {
    while (left < right) {
        int mid = left + ((right - left) >> 1) + ((right - left) & 1);
        if (check(mid)) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
}
```

When doing binary search problems, you can follow the following routine:

1. Write out the loop condition $\textit{left} < \textit{right}$;
2. Inside the loop, start with $\textit{mid} = \textit{left} + \lfloor \frac{\textit{right} - \textit{left}}{2} \rfloor$;
3. According to the specific problem, implement the $\textit{check}()$ function (sometimes the logic is very simple, you can not define $\textit{check}$), think about whether to use $\textit{right} = \textit{mid}$ (Template $1$) or $\textit{left} = \textit{mid}$ (Template $2$);
    - If $\textit{right} = \textit{mid}$, then write the else statement $\textit{left} = \textit{mid} + 1$, and keep the lower midpoint formula $\textit{mid} = \textit{left} + \lfloor \frac{\textit{right} - \textit{left}}{2} \rfloor$;
    - If $\textit{left} = \textit{mid}$, then write the else statement $\textit{right} = \textit{mid} - 1$, and add the odd remainder when calculating $\textit{mid}$, that is, $\textit{mid} = \textit{left} + \lfloor \frac{\textit{right} - \textit{left}}{2} \rfloor + ((\textit{right} - \textit{left}) \bmod 2)$;
4. When the loop ends, $\textit{left}$ equals $\textit{right}$.

Note that the advantage of these two templates is that they always keep the answer within the binary search interval, and the value corresponding to the end condition of the binary search is exactly at the position of the answer. For the case that may have no solution, just check whether the $\textit{left}$ or $\textit{right}$ after the binary search ends satisfies the problem.

## Examples

- [Find First and Last Position of Element in Sorted Array](/solution/0000-0099/0034.Find%20First%20and%20Last%20Position%20of%20Element%20in%20Sorted%20Array/README_EN.md)
- [Sqrt(x)](/solution/0000-0099/0069.Sqrt%28x%29/README_EN.md)
- [Find Peak Element](/solution/0100-0199/0162.Find%20Peak%20Element/README_EN.md)
- [First Bad Version](/solution/0200-0299/0278.First%20Bad%20Version/README_EN.md)
- [Fixed Point](/solution/1000-1099/1064.Fixed%20Point/README_EN.md)

<!-- solution:end -->
