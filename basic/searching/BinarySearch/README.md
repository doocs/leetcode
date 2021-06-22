# 二分查找

二分的本质并非“单调性”，而是“边界”，只要找到某种性质，使得整个区间一分为二，那么就可以用二分把边界点二分出来。

**整数二分算法模板：**

```java
/** 检查x是否满足某种性质 */
boolean check(int x) {}

/** 区间[left, right]被划分成[left, mid]和[mid + 1, right]时使用 */
int binarySearch1(int left, int right) {
    while (left < right) {
        int mid = (left + right) >> 1;
        if (check(mid)) right = mid;
        else left = mid + 1;
    }
    return left;
}

/** 区间[left, right] 被划分成[left, mid - 1]和[mid, right]时使用 */
int binarySearch2(int left, int right) {
    while (left < right) {
        int mid = (left + right + 1) >> 1;
        if (check(mid)) left = mid;
        else right = mid - 1;
    }
    return left;
}
```

## 例题

- [在排序数组中查找元素的第一个和最后一个位置](/solution/0000-0099/0034.Find%20First%20and%20Last%20Position%20of%20Element%20in%20Sorted%20Array/README.md)