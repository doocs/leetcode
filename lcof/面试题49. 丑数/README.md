# [面试题49. 丑数](https://leetcode-cn.com/problems/chou-shu-lcof/)

## 题目描述
<!-- 这里写题目描述 -->
我们把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。

**示例:**

```
输入: n = 10
输出: 12
解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
```

**说明:**

1. `1` 是丑数。
2. `n` 不超过 1690。


## 解法
<!-- 这里可写通用的实现逻辑 -->


### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def nthUglyNumber(self, n: int) -> int:
        if n < 7:
            return n
        dp = [1 for _ in range(n)]
        i2 = i3 = i5 = 0
        for i in range(1, n):
            next2, next3, next5 = dp[i2] * 2, dp[i3] * 3, dp[i5] * 5
            dp[i] = min(next2, next3, next5)
            if dp[i] == next2:
                i2 += 1
            if dp[i] == next3:
                i3 += 1
            if dp[i] == next5:
                i5 += 1
        return dp[n - 1]

```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int nthUglyNumber(int n) {
        if (n < 7) {
            return n;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        int i2 = 0, i3 = 0, i5= 0;
        for (int i = 1; i < n; ++i) {
            int next2 = dp[i2] * 2, next3 = dp[i3] * 3, next5 = dp[i5] * 5;
            dp[i] = Math.min(Math.min(next2, next3), next5);
            if (dp[i] == next2) {
                ++i2;
            }
            if (dp[i] == next3) {
                ++i3;
            }
            if (dp[i] == next5) {
                ++i5;
            }
        }
        return dp[n - 1];
        
    }
}
```

### JavaScript
```js
/**
 * @param {number} n
 * @return {number}
 */
var nthUglyNumber = function(n) {
    let res = [1];
    //三指针
    let a = 0;//2
    let b = 0;//3
    let c = 0;//5
    let min = 0;
    for(let i=1;i<n;i++){
        min = Math.min(res[a] * 2,res[b] * 3,res[c] * 5);
        if(min === res[a] * 2)
            a++
        if(min === res[b] * 3)
            b++
        if(min === res[c] * 5)
            c++
        res.push(min);
    }
    return res[n-1]
};
```

### ...
```

```
