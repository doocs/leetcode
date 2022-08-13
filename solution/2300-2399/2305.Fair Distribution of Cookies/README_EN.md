# [2305. Fair Distribution of Cookies](https://leetcode.com/problems/fair-distribution-of-cookies)

[中文文档](/solution/2300-2399/2305.Fair%20Distribution%20of%20Cookies/README.md)

## Description

<p>You are given an integer array <code>cookies</code>, where <code>cookies[i]</code> denotes the number of cookies in the <code>i<sup>th</sup></code> bag. You are also given an integer <code>k</code> that denotes the number of children to distribute <strong>all</strong> the bags of cookies to. All the cookies in the same bag must go to the same child and cannot be split up.</p>

<p>The <strong>unfairness</strong> of a distribution is defined as the <strong>maximum</strong> <strong>total</strong> cookies obtained by a single child in the distribution.</p>

<p>Return <em>the <strong>minimum</strong> unfairness of all distributions</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> cookies = [8,15,10,20,8], k = 2
<strong>Output:</strong> 31
<strong>Explanation:</strong> One optimal distribution is [8,15,8] and [10,20]
- The 1<sup>st</sup> child receives [8,15,8] which has a total of 8 + 15 + 8 = 31 cookies.
- The 2<sup>nd</sup> child receives [10,20] which has a total of 10 + 20 = 30 cookies.
The unfairness of the distribution is max(31,30) = 31.
It can be shown that there is no distribution with an unfairness less than 31.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> cookies = [6,1,3,2,2,4,1,2], k = 3
<strong>Output:</strong> 7
<strong>Explanation:</strong> One optimal distribution is [6,1], [3,2,2], and [4,1,2]
- The 1<sup>st</sup> child receives [6,1] which has a total of 6 + 1 = 7 cookies.
- The 2<sup>nd</sup> child receives [3,2,2] which has a total of 3 + 2 + 2 = 7 cookies.
- The 3<sup>rd</sup> child receives [4,1,2] which has a total of 4 + 1 + 2 = 7 cookies.
The unfairness of the distribution is max(7,7,7) = 7.
It can be shown that there is no distribution with an unfairness less than 7.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= cookies.length &lt;= 8</code></li>
	<li><code>1 &lt;= cookies[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= k &lt;= cookies.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
