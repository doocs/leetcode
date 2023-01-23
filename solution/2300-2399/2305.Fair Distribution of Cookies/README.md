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

**方法一：回溯 + 剪枝**

我们先对数组 $cookies$ 进行降序排序（减少搜索次数），然后创建一个长度为 $k$ 的数组 $cnt$，用于存储每个孩子分到的饼干数量。另外，用变量 $ans$ 维护当前的最小不公平程度，初始化一个很大的值。

接下来，我们从第一个零食包开始，对于当前零食包 $i$，我们枚举每个孩子 $j$，如果当前零食包中的饼干 $cookies[i]$ 分给孩子 $j$ 后，使得不公平程度大于等于 $ans$，或者当前孩子已有的饼干数量与前一个孩子相同，那么我们不需要考虑将当前零食包中的饼干分给孩子 $j$，直接跳过（剪枝）。否则，我们将当前零食包中的饼干 $cookies[i]$ 分给孩子 $j$，然后继续考虑下一个零食包。当我们考虑完所有的零食包后，更新 $ans$ 的值，然后回溯到上一个零食包，继续枚举当前零食包中的饼干分给哪个孩子。

最后，我们返回 $ans$ 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def distributeCookies(self, cookies: List[int], k: int) -> int:
        def dfs(i):
            if i >= len(cookies):
                nonlocal ans
                ans = max(cnt)
                return
            for j in range(k):
                if cnt[j] + cookies[i] >= ans or (j and cnt[j] == cnt[j - 1]):
                    continue
                cnt[j] += cookies[i]
                dfs(i + 1)
                cnt[j] -= cookies[i]

        ans = inf
        cnt = [0] * k
        cookies.sort(reverse=True)
        dfs(0)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] cookies;
    private int[] cnt;
    private int k;
    private int n;
    private int ans = 1 << 30;

    public int distributeCookies(int[] cookies, int k) {
        n = cookies.length;
        cnt = new int[k];
        // 升序排列
        Arrays.sort(cookies);
        this.cookies = cookies;
        this.k = k;
        // 这里搜索顺序是 n-1, n-2,...0
        dfs(n - 1);
        return ans;
    }

    private void dfs(int i) {
        if (i < 0) {
            // ans = Arrays.stream(cnt).max().getAsInt();
            ans = 0;
            for (int v : cnt) {
                ans = Math.max(ans, v);
            }
            return;
        }
        for (int j = 0; j < k; ++j) {
            if (cnt[j] + cookies[i] >= ans || (j > 0 && cnt[j] == cnt[j - 1])) {
                continue;
            }
            cnt[j] += cookies[i];
            dfs(i - 1);
            cnt[j] -= cookies[i];
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int distributeCookies(vector<int>& cookies, int k) {
        sort(cookies.rbegin(), cookies.rend());
        int cnt[k];
        memset(cnt, 0, sizeof cnt);
        int n = cookies.size();
        int ans = 1 << 30;
        function<void(int)> dfs = [&](int i) {
            if (i >= n) {
                ans = *max_element(cnt, cnt + k);
                return;
            }
            for (int j = 0; j < k; ++j) {
                if (cnt[j] + cookies[i] >= ans || (j && cnt[j] == cnt[j - 1])) {
                    continue;
                }
                cnt[j] += cookies[i];
                dfs(i + 1);
                cnt[j] -= cookies[i];
            }
        };
        dfs(0);
        return ans;
    }
};
```

### **Go**

```go
func distributeCookies(cookies []int, k int) int {
	sort.Sort(sort.Reverse(sort.IntSlice(cookies)))
	cnt := make([]int, k)
	ans := 1 << 30
	var dfs func(int)
	dfs = func(i int) {
		if i >= len(cookies) {
			ans = 0
			for _, v := range cnt {
				ans = max(ans, v)
			}
			return
		}
		for j := 0; j < k; j++ {
			if cnt[j]+cookies[i] >= ans || (j > 0 && cnt[j] == cnt[j-1]) {
				continue
			}
			cnt[j] += cookies[i]
			dfs(i + 1)
			cnt[j] -= cookies[i]
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
```

### **TypeScript**

```ts
function distributeCookies(cookies: number[], k: number): number {
    const cnt = new Array(k).fill(0);
    let ans = 1 << 30;
    const dfs = (i: number) => {
        if (i >= cookies.length) {
            ans = Math.max(...cnt);
            return;
        }
        for (let j = 0; j < k; ++j) {
            if (cnt[j] + cookies[i] >= ans || (j && cnt[j] == cnt[j - 1])) {
                continue;
            }
            cnt[j] += cookies[i];
            dfs(i + 1);
            cnt[j] -= cookies[i];
        }
    };
    dfs(0);
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
