<!-- solution:start -->

# 二分查找

二分的本质并非“单调性”，而是“边界”，只要找到某种性质，使得整个区间一分为二，那么就可以用二分把边界点二分出来。

以下模板假设 `0 <= left <= right` 且两者为 32 位 `int` 边界。中点从差值计算，避免 `left + right` 溢出。

## 算法模板

### 模板 1

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

### 模板 2

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

做二分题目时，可以按照以下套路：

1. 写出循环条件 $\textit{left} < \textit{right}$；
1. 循环体内，不妨先写 $\textit{mid} = \textit{left} + \lfloor \frac{\textit{right} - \textit{left}}{2} \rfloor$；
1. 根据具体题目，实现 $\textit{check}()$ 函数（有时很简单的逻辑，可以不定义 $\textit{check}$），想一下究竟要用 $\textit{right} = \textit{mid}$（模板 $1$） 还是 $\textit{left} = \textit{mid}$（模板 $2$）；
       - 如果 $\textit{right} = \textit{mid}$，那么写出 else 语句 $\textit{left} = \textit{mid} + 1$，并且不需要更改 mid 的计算，即保持 $\textit{mid} = \textit{left} + \lfloor \frac{\textit{right} - \textit{left}}{2} \rfloor$；
       - 如果 $\textit{left} = \textit{mid}$，那么写出 else 语句 $\textit{right} = \textit{mid} - 1$，并且在 $\textit{mid}$ 计算时补上奇数余数，即 $\textit{mid} = \textit{left} + \lfloor \frac{\textit{right} - \textit{left}}{2} \rfloor + ((\textit{right} - \textit{left}) \bmod 2)$；
1. 循环结束时， $\textit{left}$ 与 $\textit{right}$ 相等。

注意，这两个模板的优点是始终保持答案位于二分区间内，二分结束条件对应的值恰好在答案所处的位置。 对于可能无解的情况，只要判断二分结束后的 $\textit{left}$ 或者 $\textit{right}$ 是否满足题意即可。

## 例题

- [在排序数组中查找元素的第一个和最后一个位置](/solution/0000-0099/0034.Find%20First%20and%20Last%20Position%20of%20Element%20in%20Sorted%20Array/README.md)
- [x 的平方根](/solution/0000-0099/0069.Sqrt%28x%29/README.md)
- [寻找峰值](/solution/0100-0199/0162.Find%20Peak%20Element/README.md)
- [第一个错误的版本](/solution/0200-0299/0278.First%20Bad%20Version/README.md)
- [不动点](/solution/1000-1099/1064.Fixed%20Point/README.md)

<!-- solution:end -->
