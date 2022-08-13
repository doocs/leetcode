# [2007. 从双倍数组中还原原数组](https://leetcode.cn/problems/find-original-array-from-doubled-array)

[English Version](/solution/2000-2099/2007.Find%20Original%20Array%20From%20Doubled%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个整数数组&nbsp;<code>original</code>&nbsp;可以转变成一个 <strong>双倍</strong>&nbsp;数组&nbsp;<code>changed</code>&nbsp;，转变方式为将 <code>original</code>&nbsp;中每个元素 <strong>值乘以 2 </strong>加入数组中，然后将所有元素 <strong>随机打乱</strong>&nbsp;。</p>

<p>给你一个数组&nbsp;<code>changed</code>&nbsp;，如果&nbsp;<code>change</code>&nbsp;是&nbsp;<strong>双倍</strong>&nbsp;数组，那么请你返回&nbsp;<code>original</code>数组，否则请返回空数组。<code>original</code>&nbsp;的元素可以以&nbsp;<strong>任意</strong>&nbsp;顺序返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>changed = [1,3,4,2,6,8]
<b>输出：</b>[1,3,4]
<b>解释：</b>一个可能的 original 数组为 [1,3,4] :
- 将 1 乘以 2 ，得到 1 * 2 = 2 。
- 将 3 乘以 2 ，得到 3 * 2 = 6 。
- 将 4 乘以 2 ，得到 4 * 2 = 8 。
其他可能的原数组方案为 [4,3,1] 或者 [3,1,4] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>changed = [6,3,0,1]
<b>输出：</b>[]
<b>解释：</b>changed 不是一个双倍数组。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>changed = [1]
<b>输出：</b>[]
<b>解释：</b>changed 不是一个双倍数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= changed.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= changed[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findOriginalArray(self, changed: List[int]) -> List[int]:
        if len(changed) % 2 != 0:
            return []
        n = 100010
        counter = [0] * n
        for x in changed:
            counter[x] += 1
        if counter[0] % 2 != 0:
            return []
        res = [0] * (counter[0] // 2)
        for i in range(1, n):
            if counter[i] == 0:
                continue
            if i * 2 > n or counter[i] > counter[i * 2]:
                return []
            res.extend([i] * counter[i])
            counter[i * 2] -= counter[i]
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] findOriginalArray(int[] changed) {
        if (changed.length % 2 != 0) {
            return new int[]{};
        }
        int n = 100010;
        int[] counter = new int[n];
        for (int x : changed) {
            ++counter[x];
        }
        if (counter[0] % 2 != 0) {
            return new int[]{};
        }
        int[] res = new int[changed.length / 2];
        int j = counter[0] / 2;
        for (int i = 1; i < n; ++i) {
            if (counter[i] == 0) {
                continue;
            }
            if (i * 2 >= n || counter[i] > counter[i * 2]) {
                return new int[]{};
            }
            counter[i * 2] -= counter[i];
            while (counter[i]-- > 0) {
                res[j++] = i;
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> findOriginalArray(vector<int>& changed) {
        if (changed.size() % 2 != 0) return {};
        int n = 100010;
        vector<int> counter(n);
        for (int x : changed) ++counter[x];
        if (counter[0] % 2 != 0) return {};
        vector<int> res(changed.size() / 2);
        int j = counter[0] / 2;
        for (int i = 1; i < n; ++i) {
            if (counter[i] == 0) continue;
            if (i * 2 >= n || counter[i] > counter[i * 2]) return {};
            counter[i * 2] -= counter[i];
            while (counter[i]--) res[j++] = i;
        }
        return res;
    }
};
```

### **Go**

```go
func findOriginalArray(changed []int) []int {
	if len(changed)%2 != 0 {
		return []int{}
	}
	n := 100010
	counter := make([]int, n)
	for _, x := range changed {
		counter[x]++
	}
	if counter[0]%2 != 0 {
		return []int{}
	}
	var res []int
	for j := 0; j < counter[0]/2; j++ {
		res = append(res, 0)
	}
	for i := 1; i < n; i++ {
		if counter[i] == 0 {
			continue
		}
		if i*2 >= n || counter[i] > counter[i*2] {
			return []int{}
		}
		for j := 0; j < counter[i]; j++ {
			res = append(res, i)
		}
		counter[i*2] -= counter[i]
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
