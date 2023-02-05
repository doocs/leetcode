# [2555. 两个线段获得的最多奖品](https://leetcode.cn/problems/maximize-win-from-two-segments)

[English Version](/solution/2500-2599/2555.Maximize%20Win%20From%20Two%20Segments/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在 <strong>X轴</strong>&nbsp;上有一些奖品。给你一个整数数组&nbsp;<code>prizePositions</code>&nbsp;，它按照 <strong>非递减</strong>&nbsp;顺序排列，其中&nbsp;<code>prizePositions[i]</code>&nbsp;是第&nbsp;<code>i</code>&nbsp;件奖品的位置。数轴上一个位置可能会有多件奖品。再给你一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p>你可以选择两个端点为整数的线段。每个线段的长度都必须是 <code>k</code>&nbsp;。你可以获得位置在任一线段上的所有奖品（包括线段的两个端点）。注意，两个线段可能会有相交。</p>

<ul>
	<li>比方说&nbsp;<code>k = 2</code>&nbsp;，你可以选择线段&nbsp;<code>[1, 3]</code> 和&nbsp;<code>[2, 4]</code>&nbsp;，你可以获得满足&nbsp;<code>1 &lt;= prizePositions[i] &lt;= 3</code> 或者&nbsp;<code>2 &lt;= prizePositions[i] &lt;= 4</code>&nbsp;的所有奖品 i 。</li>
</ul>

<p>请你返回在选择两个最优线段的前提下，可以获得的 <strong>最多</strong>&nbsp;奖品数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>prizePositions = [1,1,2,2,3,3,5], k = 2
<b>输出：</b>7
<b>解释：</b>这个例子中，你可以选择线段 [1, 3] 和 [3, 5] ，获得 7 个奖品。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>prizePositions = [1,2,3,4], k = 0
<b>输出：</b>2
<b>解释：</b>这个例子中，一个选择是选择线段 <code>[3, 3]</code> 和 <code>[4, 4] ，获得 2 个奖品。</code>
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= prizePositions.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= prizePositions[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>9</sup> </code></li>
	<li><code>prizePositions</code>&nbsp;有序非递减。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划 + 二分查找**

我们定义 $f[i]$ 表示在前 $i$ 个奖品中，选择一个长度为 $k$ 的线段，可以获得的最多奖品数目。初始时 $f[0] = 0$。定义答案变量 $ans = 0$。

接下来，我们枚举每个奖品的位置 $x$，通过二分查找，找到最左边的奖品下标 $j$，使得 $prizePositions[j] \geq x - k$。此时，我们更新答案 $ans = \max(ans, f[j] + i - j)$，并更新 $f[i] = \max(f[i - 1], i - j)$。

最后，返回 $ans$ 即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为奖品数目。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximizeWin(self, prizePositions: List[int], k: int) -> int:
        n = len(prizePositions)
        f = [0] * (n + 1)
        ans = 0
        for i, x in enumerate(prizePositions, 1):
            j = bisect_left(prizePositions, x - k)
            ans = max(ans, f[j] + i - j)
            f[i] = max(f[i - 1], i - j)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximizeWin(int[] prizePositions, int k) {
        int n = prizePositions.length;
        int[] f = new int[n + 1];
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            int x = prizePositions[i - 1];
            int j = search(prizePositions, x - k);
            ans = Math.max(ans, f[j] + i - j);
            f[i] = Math.max(f[i - 1], i - j);
        }
        return ans;
    }

    private int search(int[] nums, int x) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximizeWin(vector<int>& prizePositions, int k) {
        int n = prizePositions.size();
        vector<int> f(n + 1);
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            int x = prizePositions[i - 1];
            int j = lower_bound(prizePositions.begin(), prizePositions.end(), x - k) - prizePositions.begin();
            ans = max(ans, f[j] + i - j);
            f[i] = max(f[i - 1], i - j);
        }
        return ans;
    }
};
```

### **Go**

```go
func maximizeWin(prizePositions []int, k int) (ans int) {
	n := len(prizePositions)
	f := make([]int, n+1)
	for i, x := range prizePositions {
		j := sort.Search(n, func(h int) bool { return prizePositions[h] >= x-k })
		ans = max(ans, f[j]+i-j+1)
		f[i+1] = max(f[i], i-j+1)
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

### **...**

```

```

<!-- tabs:end -->
