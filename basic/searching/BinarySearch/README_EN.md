# Binary Search

## Algorithm Templates

### Template 1

```java
boolean check(int x) {}

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
boolean check(int x) {}

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

## Examples

-   [Find First and Last Position of Element in Sorted Array](/solution/0000-0099/0034.Find%20First%20and%20Last%20Position%20of%20Element%20in%20Sorted%20Array/README_EN.md)
-   [Sqrt(x)](/solution/0000-0099/0069.Sqrt%28x%29/README_EN.md)
-   [Find Peak Element](/solution/0100-0199/0162.Find%20Peak%20Element/README_EN.md)
-   [First Bad Version](/solution/0200-0299/0278.First%20Bad%20Version/README_EN.md)
-   [Fixed Point](/solution/1000-1099/1064.Fixed%20Point/README_EN.md)
