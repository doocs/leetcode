# [2107. 分享 K 个糖果后独特口味的数量](https://leetcode.cn/problems/number-of-unique-flavors-after-sharing-k-candies)

[English Version](/solution/2100-2199/2107.Number%20of%20Unique%20Flavors%20After%20Sharing%20K%20Candies/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>您将获得一个 <strong>从0开始的</strong> 整数数组 <code>candies</code> ，其中 <code>`candies[i]`</code>表示第 <code>i</code> 个糖果的味道。你妈妈想让你和你妹妹分享这些糖果，给她 <code>k</code> 个 <strong>连续 </strong>的糖果，但你想保留尽可能多的糖果口味。<br />
在与妹妹分享后，返回 <strong>最多</strong> 可保留的 <strong>独特</strong> 口味的糖果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> candies = [1,<u>2,2,3</u>,4,3], k = 3
<strong>输出:</strong> 3
<strong>解释:</strong>
将[1,3]（含[2,2,3]）范围内的糖果加入[2,2,3]口味。
你可以吃各种口味的糖果[1,4,3]。
有3种独特的口味，所以返回3。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> candies = [2,2,2,<u>2,3</u>,3], k = 2
<strong>输出:</strong> 2
<strong>解释:</strong>
在[3,4]范围内（含[2,3]）的糖果中加入[2,3]口味。
你可以吃各种口味的糖果[2,2,2,3]。
有两种独特的口味，所以返回2。
请注意，你也可以分享口味为[2,2]的糖果，吃口味为[2,2,3,3]的糖果。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> candies = [2,4,5], k = 0
<strong>输出:</strong> 3
<strong>解释:</strong>
你不必给任何糖果。
你可以吃各种口味的糖果[2,4,5]。
有3种独特的口味，所以返回3。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= candies.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= candies[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= candies.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：滑动窗口 + 哈希表**

我们可以维护一个大小为 $k$ 的滑动窗口，窗口外的糖果为自己的，窗口内的 $k$ 个糖果分给妹妹和妈妈。我们可以用哈希表 $cnt$ 记录窗口外的糖果口味以及对应的数量。

初始时，哈希表 $cnt$ 中存储的是 $candies[k]$ 到 $candies[n-1]$ 的糖果口味以及对应的数量。此时糖果口味的种类数为哈希表 $cnt$ 的大小，即 $ans = cnt.size()$。

接下来，我们遍历 $[k,..n-1]$ 范围内的糖果，将当前糖果 $candies[i]$ 加入窗口内，同时将窗口左侧的糖果 $candies[i-k]$ 移出窗口外。然后我们更新哈希表 $cnt$，并且更新糖果口味的种类数 $ans$ 为 $max(ans, cnt.size())$。

遍历完所有糖果后，我们即可得到最多可保留的独特口味的糖果。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为糖果的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def shareCandies(self, candies: List[int], k: int) -> int:
        cnt = Counter(candies[k:])
        ans = len(cnt)
        for i in range(k, len(candies)):
            cnt[candies[i]] -= 1
            cnt[candies[i - k]] += 1
            if cnt[candies[i]] == 0:
                cnt.pop(candies[i])
            ans = max(ans, len(cnt))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int shareCandies(int[] candies, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int n = candies.length;
        for (int i = k; i < n; ++i) {
            cnt.merge(candies[i], 1, Integer::sum);
        }
        int ans = cnt.size();
        for (int i = k; i < candies.length; ++i) {
            if (cnt.merge(candies[i], -1, Integer::sum) == 0) {
                cnt.remove(candies[i]);
            }
            cnt.merge(candies[i - k], 1, Integer::sum);
            ans = Math.max(ans, cnt.size());
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int shareCandies(vector<int>& candies, int k) {
        unordered_map<int, int> cnt;
        int n = candies.size();
        for (int i = k; i < n; ++i) {
            ++cnt[candies[i]];
        }
        int ans = cnt.size();
        for (int i = k; i < candies.size(); ++i) {
            if (--cnt[candies[i]] == 0) {
                cnt.erase(candies[i]);
            }
            ++cnt[candies[i - k]];
            ans = max(ans, (int) cnt.size());
        }
        return ans;
    }
};
```

### **Go**

```go
func shareCandies(candies []int, k int) (ans int) {
	cnt := map[int]int{}
	for _, c := range candies[k:] {
		cnt[c]++
	}
	ans = len(cnt)
	for i := k; i < len(candies); i++ {
		cnt[candies[i]]--
		if cnt[candies[i]] == 0 {
			delete(cnt, candies[i])
		}
		cnt[candies[i-k]]++
		ans = max(ans, len(cnt))
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
