# 二分查找

二分的本质并非“单调性”，而是“边界”，只要找到某种性质，使得整个区间一分为二，那么就可以用二分把边界点二分出来。

整数二分算法模板：

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

## 题目描述

给定一个按照升序排列的长度为 `n` 的整数数组，以及 `q` 个查询。

对于每个查询，返回一个元素 k 的起始位置和终止位置（位置从 0 开始计数）。

如果数组中不存在该元素，则返回 `-1 -1`。

**输入格式**

第一行包含整数 n 和 q，表示数组长度和询问个数。

第二行包含  n  个整数（均在 1∼10000 范围内），表示完整数组。

接下来 q 行，每行包含一个整数 k，表示一个询问元素。

**输出格式**

共 q 行，每行包含两个整数，表示所求元素的起始位置和终止位置。

如果数组中不存在该元素，则返回 `-1 -1`。

**数据范围**

- 1≤n≤100000
- 1≤q≤10000
- 1≤k≤10000

**输入样例：**

```
6 3
1 2 2 3 3 4
3
4
5
```

**输出样例：**

```
3 4
5 5
-1 -1
```

## 代码实现

<!-- tabs:start -->

### **Python3**

```python
n, q = map(int, input().split())
nums = list(map(int, input().split()))

for _ in range(q):
    x = int(input())
    left, right = 0, n - 1
    while left < right:
        mid = (left + right) >> 1
        if nums[mid] >= x:
            right = mid
        else:
            left = mid + 1
    if nums[left] != x:
        print('-1 -1')
    else:
        t = left
        left, right = 0, n - 1
        while left < right:
            mid = (left + right + 1) >> 1
            if nums[mid] <= x:
                left = mid
            else:
                right = mid - 1
        print(f'{t} {left}')
```

### **Java**

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), q = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = sc.nextInt();
        }
        while (q-- > 0) {
            int x = sc.nextInt();
            int left = 0, right = n - 1;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (nums[mid] >= x) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (nums[left] != x) {
                System.out.println("-1 -1");
            } else {
                int t = left;
                left = 0;
                right = n - 1;
                while (left < right) {
                    int mid = (left + right + 1) >> 1;
                    if (nums[mid] <= x) {
                        left = mid;
                    } else {
                        right = mid - 1;
                    }
                }
                System.out.printf("%d %d\n", t, left);
            }
        }
    }
}
```

<!-- tabs:end -->