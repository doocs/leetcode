# [2305. 公平分发饼干](https://leetcode.cn/problems/fair-distribution-of-cookies)

[English Version](/solution/2300-2399/2305.Fair%20Distribution%20of%20Cookies/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>cookies</code> ，其中 <code>cookies[i]</code> 表示在第 <code>i</code> 个零食包中的饼干数量。另给你一个整数 <code>k</code> 表示等待分发零食包的孩子数量，<strong>所有</strong> 零食包都需要分发。在同一个零食包中的所有饼干都必须分发给同一个孩子，不能分开。</p>

<p>分发的 <strong>不公平程度</strong> 定义为单个孩子在分发过程中能够获得饼干的最大总数。</p>

<p>返回所有分发的最小不公平程度。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>cookies = [8,15,10,20,8], k = 2
<strong>输出：</strong>31
<strong>解释：</strong>一种最优方案是 [8,15,8] 和 [10,20] 。
- 第 1 个孩子分到 [8,15,8] ，总计 8 + 15 + 8 = 31 块饼干。
- 第 2 个孩子分到 [10,20] ，总计 10 + 20 = 30 块饼干。
分发的不公平程度为 max(31,30) = 31 。
可以证明不存在不公平程度小于 31 的分发方案。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>cookies = [6,1,3,2,2,4,1,2], k = 3
<strong>输出：</strong>7
<strong>解释：</strong>一种最优方案是 [6,1]、[3,2,2] 和 [4,1,2] 。
- 第 1 个孩子分到 [6,1] ，总计 6 + 1 = 7 块饼干。 
- 第 2 个孩子分到 [3,2,2] ，总计 3 + 2 + 2 = 7 块饼干。
- 第 3 个孩子分到 [4,1,2] ，总计 4 + 1 + 2 = 7 块饼干。
分发的不公平程度为 max(7,7,7) = 7 。
可以证明不存在不公平程度小于 7 的分发方案。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= cookies.length &lt;= 8</code></li>
	<li><code>1 &lt;= cookies[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= k &lt;= cookies.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS + 剪枝**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def distributeCookies(self, cookies: List[int], k: int) -> int:
        def dfs(u):
            nonlocal ans
            if u == len(cookies):
                ans = min(ans, max(cnt))
                return
            for i in range(k):
                if cnt[i] + cookies[u] < ans:
                    cnt[i] += cookies[u]
                    dfs(u + 1)
                    cnt[i] -= cookies[u]

        ans = inf
        cnt = [0] * k
        dfs(0)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] cookies;
    private int k;
    private int[] cnt;
    private int ans;

    public int distributeCookies(int[] cookies, int k) {
        ans = 0x3f3f3f3f;
        this.cookies = cookies;
        this.k = k;
        this.cnt = new int[k];
        dfs(0);
        return ans;
    }

    private void dfs(int u) {
        if (u == cookies.length) {
            int mx = cnt[0];
            for (int v : cnt) {
                mx = Math.max(mx, v);
            }
            ans = Math.min(ans, mx);
            return;
        }
        for (int i = 0; i < k; ++i) {
            if (cnt[i] + cookies[u] < ans) {
                cnt[i] += cookies[u];
                dfs(u + 1);
                cnt[i] -= cookies[u];
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> cookies;
    vector<int> cnt;
    int k;
    int ans;

    int distributeCookies(vector<int>& cookies, int k) {
        ans = 0x3f3f3f3f;
        this->cookies = cookies;
        this->cnt = vector<int>(k);
        this->k = k;
        dfs(0);
        return ans;
    }

    void dfs(int u) {
        if (u == cookies.size()) {
            ans = min(ans, *max_element(cnt.begin(), cnt.end()));
            return;
        }
        for (int i = 0; i < k; ++i) {
            if (cnt[i] + cookies[u] < ans) {
                cnt[i] += cookies[u];
                dfs(u + 1);
                cnt[i] -= cookies[u];
            }
        }
    }
};
```

### **Go**

```go
func distributeCookies(cookies []int, k int) int {
	cnt := make([]int, k)
	ans := 0x3f3f3f3f
	var dfs func(int)
	dfs = func(u int) {
		if u == len(cookies) {
			mx := cnt[0]
			for _, v := range cnt {
				mx = max(mx, v)
			}
			ans = min(ans, mx)
			return
		}
		for i := 0; i < k; i++ {
			if cnt[i]+cookies[u] < ans {
				cnt[i] += cookies[u]
				dfs(u + 1)
				cnt[i] -= cookies[u]
			}
		}
	}
	dfs(0)
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
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
