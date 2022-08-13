# [2180. 统计各位数字之和为偶数的整数个数](https://leetcode.cn/problems/count-integers-with-even-digit-sum)

[English Version](/solution/2100-2199/2180.Count%20Integers%20With%20Even%20Digit%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数 <code>num</code> ，请你统计并返回 <strong>小于或等于</strong> <code>num</code> 且各位数字之和为 <strong>偶数</strong> 的正整数的数目。</p>

<p>正整数的 <strong>各位数字之和</strong> 是其所有位上的对应数字相加的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num = 4
<strong>输出：</strong>2
<strong>解释：</strong>
只有 2 和 4 满足小于等于 4 且各位数字之和为偶数。    
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num = 30
<strong>输出：</strong>14
<strong>解释：</strong>
只有 14 个整数满足小于等于 30 且各位数字之和为偶数，分别是： 
2、4、6、8、11、13、15、17、19、20、22、24、26 和 28 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countEven(self, num: int) -> int:
        ans = 0
        for i in range(1, num + 1):
            t = 0
            while i:
                t += i % 10
                i //= 10
            if t % 2 == 0:
                ans += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countEven(int num) {
        int ans = 0;
        for (int i = 1; i <= num; ++i) {
            int j = i, t = 0;
            while (j > 0) {
                t += j % 10;
                j /= 10;
            }
            if (t % 2 == 0) {
                ++ans;
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function countEven(num: number): number {
    let ans = 0;
    for (let i = 2; i <= num; i++) {
        if ([...String(i)].reduce((a, c) => a + Number(c), 0) % 2 == 0) {
            ans++;
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int countEven(int num) {
        int ans = 0;
        for (int i = 1; i <= num; ++i) {
            int t = 0;
            for (int j = i; j; j /= 10) t += j % 10;
            if (t % 2 == 0) ++ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func countEven(num int) int {
	ans := 0
	for i := 1; i <= num; i++ {
		t := 0
		for j := i; j > 0; j /= 10 {
			t += j % 10
		}
		if t%2 == 0 {
			ans++
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
