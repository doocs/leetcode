# Binary Search

## Algorithm Templates

The essence of binary search is not "monotonicity", but "boundary". As long as a certain property is found that divides the entire interval into two, the boundary point can be found using binary search.

### Template 1

```java
boolean check(int x) {
}

int search(int left, int right) {
    while (left < right) {
        int mid = (left + right) >> 1;
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
        int mid = (left + right + 1) >> 1;
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

1. Write out the loop condition $left < right$;
2. Inside the loop, you might as well write $mid = \lfloor \frac{left + right}{2} \rfloor$ first;
3. According to the specific problem, implement the $check()$ function (sometimes the logic is very simple, you can not define $check$), think about whether to use $right = mid$ (Template $1$) or $left = mid$ (Template $2$);
    - If $right = mid$, then write the else statement $left = mid + 1$, and there is no need to change the calculation of $mid$, that is, keep $mid = \lfloor \frac{left + right}{2} \rfloor$;
    - If $left = mid$, then write the else statement $right = mid - 1$, and add +1 when calculating $mid$, that is, $mid = \lfloor \frac{left + right + 1}{2} \rfloor$;
4. When the loop ends, $left$ equals $right$.

Note that the advantage of these two templates is that they always keep the answer within the binary search interval, and the value corresponding to the end condition of the binary search is exactly at the position of the answer. For the case that may have no solution, just check whether the $left$ or $right$ after the binary search ends satisfies the problem.

## Examples

-   [Find First and Last Position of Element in Sorted Array](/solution/0000-0099/0034.Find%20First%20and%20Last%20Position%20of%20Element%20in%20Sorted%20Array/README_EN.md)
-   [Sqrt(x)](/solution/0000-0099/0069.Sqrt%28x%29/README_EN.md)
-   [Find Peak Element](/solution/0100-0199/0162.Find%20Peak%20Element/README_EN.md)
-   [First Bad Version](/solution/0200-0299/0278.First%20Bad%20Version/README_EN.md)
-   [Fixed Point](/solution/1000-1099/1064.Fixed%20Point/README_EN.md)
