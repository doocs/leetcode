---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2682.Find%20the%20Losers%20of%20the%20Circular%20Game/README.md
rating: 1382
tags:
    - 数组
    - 哈希表
    - 模拟
---

# [2682. 找出转圈游戏输家](https://leetcode.cn/problems/find-the-losers-of-the-circular-game)

[English Version](/solution/2600-2699/2682.Find%20the%20Losers%20of%20the%20Circular%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code>n</code> 个朋友在玩游戏。这些朋友坐成一个圈，按 <strong>顺时针方向</strong> 从 <code>1</code> 到 <code>n</code> 编号。准确的说，从第 <code>i</code> 个朋友的位置开始顺时针移动 <code>1</code> 步会到达第 <code>(i + 1)</code> 个朋友的位置（<code>1 &lt;= i &lt; n</code>），而从第 <code>n</code> 个朋友的位置开始顺时针移动 <code>1</code> 步会回到第 <code>1</code> 个朋友的位置。</p>

<p>游戏规则如下：</p>

<p>第 <code>1</code> 个朋友接球。</p>

<ul>
	<li>接着，第 <code>1</code> 个朋友将球传给距离他顺时针方向 <code>k</code> 步的朋友。</li>
	<li>然后，接球的朋友应该把球传给距离他顺时针方向 <code>2 * k</code> 步的朋友。</li>
	<li>接着，接球的朋友应该把球传给距离他顺时针方向 <code>3 * k</code> 步的朋友，以此类推。</li>
</ul>

<p>换句话说，在第 <code>i</code> 轮中持有球的那位朋友需要将球传递给距离他顺时针方向 <code>i * k</code> 步的朋友。</p>

<p>当某个朋友第 2 次接到球时，游戏结束。</p>

<p>在整场游戏中没有接到过球的朋友是 <strong>输家</strong> 。</p>

<p>给你参与游戏的朋友数量 <code>n</code> 和一个整数 <code>k</code> ，请按升序排列返回包含所有输家编号的数组 <code>answer</code> 作为答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 5, k = 2
<strong>输出：</strong>[4,5]
<strong>解释：</strong>以下为游戏进行情况：
1）第 1 个朋友接球，第 1 个朋友将球传给距离他顺时针方向 2 步的玩家 —— 第 3 个朋友。
2）第 3 个朋友将球传给距离他顺时针方向 4 步的玩家 —— 第 2 个朋友。
3）第 2 个朋友将球传给距离他顺时针方向 6 步的玩家 —— 第 3 个朋友。
4）第 3 个朋友接到两次球，游戏结束。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 4, k = 4
<strong>输出：</strong>[2,3,4]
<strong>解释：</strong>以下为游戏进行情况：
1）第 1 个朋友接球，第 1 个朋友将球传给距离他顺时针方向 4 步的玩家 —— 第 1 个朋友。
2）第 1 个朋友接到两次球，游戏结束。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= n &lt;= 50</code></li>
</ul>

## 解法

### 方法一：模拟

我们用一个数组 `vis` 记录每个朋友是否接到过球，初始时所有朋友都没有接到过球。然后我们按照题目描述的规则模拟游戏的过程，直到某个朋友第二次接到球为止。

在模拟过程中，我们用两个变量 $i$ 和 $p$ 分别表示当前持球的朋友编号和当前传球的步长。初始时 $i=0,p=1$，表示第一个朋友接到球。每次传球时，我们将 $i$ 更新为 $(i+p \times k) \bmod n$，表示下一个接球的朋友编号，然后将 $p$ 更新为 $p+1$，表示下一次传球的步长。当某个朋友第二次接到球时，游戏结束。

最后我们遍历数组 `vis`，将没有接到过球的朋友编号加入答案数组中即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是朋友的数量。

<!-- tabs:start -->

```python
class Solution:
    def circularGameLosers(self, n: int, k: int) -> List[int]:
        vis = [False] * n
        i, p = 0, 1
        while not vis[i]:
            vis[i] = True
            i = (i + p * k) % n
            p += 1
        return [i + 1 for i in range(n) if not vis[i]]
```

```java
class Solution {
    public int[] circularGameLosers(int n, int k) {
        boolean[] vis = new boolean[n];
        int cnt = 0;
        for (int i = 0, p = 1; !vis[i]; ++p) {
            vis[i] = true;
            ++cnt;
            i = (i + p * k) % n;
        }
        int[] ans = new int[n - cnt];
        for (int i = 0, j = 0; i < n; ++i) {
            if (!vis[i]) {
                ans[j++] = i + 1;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> circularGameLosers(int n, int k) {
        bool vis[n];
        memset(vis, false, sizeof(vis));
        for (int i = 0, p = 1; !vis[i]; ++p) {
            vis[i] = true;
            i = (i + p * k) % n;
        }
        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                ans.push_back(i + 1);
            }
        }
        return ans;
    }
};
```

```go
func circularGameLosers(n int, k int) (ans []int) {
	vis := make([]bool, n)
	for i, p := 0, 1; !vis[i]; p++ {
		vis[i] = true
		i = (i + p*k) % n
	}
	for i, x := range vis {
		if !x {
			ans = append(ans, i+1)
		}
	}
	return
}
```

```ts
function circularGameLosers(n: number, k: number): number[] {
    const vis = new Array(n).fill(false);
    const ans: number[] = [];
    for (let i = 0, p = 1; !vis[i]; p++) {
        vis[i] = true;
        i = (i + p * k) % n;
    }
    for (let i = 0; i < vis.length; i++) {
        if (!vis[i]) {
            ans.push(i + 1);
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn circular_game_losers(n: i32, k: i32) -> Vec<i32> {
        let mut vis: Vec<bool> = vec![false; n as usize];

        let mut i = 0;
        let mut p = 1;
        while !vis[i] {
            vis[i] = true;
            i = (i + p * (k as usize)) % (n as usize);
            p += 1;
        }

        let mut ans = Vec::new();
        for i in 0..vis.len() {
            if !vis[i] {
                ans.push((i + 1) as i32);
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
