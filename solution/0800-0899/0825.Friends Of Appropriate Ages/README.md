# [825. 适龄的朋友](https://leetcode-cn.com/problems/friends-of-appropriate-ages)

[English Version](/solution/0800-0899/0825.Friends%20Of%20Appropriate%20Ages/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>人们会互相发送好友请求，现在给定一个包含有他们年龄的数组，<code>ages[i]</code> 表示第 i 个人的年龄。</p>

<p>当满足以下任一条件时，A 不能给 B（A、B不为同一人）发送好友请求：</p>

<ul>
	<li><code>age[B] <= 0.5 * age[A] + 7</code></li>
	<li><code>age[B] > age[A]</code></li>
	<li><code>age[B] > 100 && age[A] < 100</code></li>
</ul>

<p>否则，A 可以给 B 发送好友请求。</p>

<p>注意如果 A 向 B 发出了请求，不等于 B 也一定会向 A 发出请求。而且，人们不会给自己发送好友请求。 </p>

<p>求总共会发出多少份好友请求?</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>[16,16]
<strong>输出：</strong>2
<strong>解释：</strong>二人可以互发好友申请。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>[16,17,18]
<strong>输出：</strong>2
<strong>解释：</strong>好友请求可产生于 17 -> 16, 18 -> 17.</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>[20,30,100,110,120]
<strong>输出：</strong>3
<strong>解释：</strong>好友请求可产生于 110 -> 100, 120 -> 110, 120 -> 100.
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= ages.length <= 20000</code></li>
	<li><code>1 <= ages[i] <= 120</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

对年龄计数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numFriendRequests(self, ages: List[int]) -> int:
        def check(a, b):
            return (0.5 * a + 7 < b) and (a >= b) and (a >= 100 or b <= 100)

        res = 0
        counter = [0] * 121
        for age in ages:
            counter[age] += 1
        for i in range(1, 121):
            n1 = counter[i]
            for j in range(1, 121):
                if check(i, j):
                    n2 = counter[j]
                    res += (n1 * n2)
                    if i == j:
                        res -= n2
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numFriendRequests(int[] ages) {
        int[] counter = new int[121];
        for (int age : ages) {
            ++counter[age];
        }
        int res = 0;
        for (int i = 1; i < 121; ++i) {
            int n1 = counter[i];
            for (int j = 1; j < 121; ++j) {
                if (check(i, j)) {
                    int n2 = counter[j];
                    res += (n1 * n2);
                    if (i == j) {
                        res -= n2;
                    }
                }

            }
        }
        return res;
    }

    private boolean check(int a, int b) {
        return (0.5 * a + 7 < b) && (a >= b) && (a >= 100 || b <= 100);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numFriendRequests(vector<int>& ages) {
        vector<int> counter(121);
        for (int age : ages) ++counter[age];
        int res = 0;
        for (int i = 1; i < 121; ++i)
        {
            int n1 = counter[i];
            for (int j = 1; j < 121; ++j)
            {
                int n2 = counter[j];
                if (check(i, j))
                {
                    res += (n1 * n2);
                    if (i == j) res -= n2;
                }
            }
        }
        return res;
    }

    bool check(int a, int b) {
        return (0.5 * a + 7 < b) && (a >= b) && (a >= 100 || b <= 100);
    }
};
```

### **Go**

```go
func numFriendRequests(ages []int) int {
	counter := make([]int, 121)
	for _, age := range ages {
		counter[age]++
	}
	res := 0
	for i := 1; i < 121; i++ {
		n1 := counter[i]
		for j := 1; j < 121; j++ {
			n2 := counter[j]
			if check(i, j) {
				res += (n1 * n2)
				if i == j {
					res -= n2
				}
			}
		}
	}
	return res
}

func check(a, b int) bool {
	return (a/2+7 < b) && (a >= b) && (a >= 100 || b <= 100)
}
```

### **...**

```

```

<!-- tabs:end -->
