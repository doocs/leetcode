# [2555. Maximize Win From Two Segments](https://leetcode.com/problems/maximize-win-from-two-segments)

[中文文档](/solution/2500-2599/2555.Maximize%20Win%20From%20Two%20Segments/README.md)

<!-- tags:Array,Binary Search,Sliding Window -->

<!-- difficulty:Medium -->

## Description

<p>There are some prizes on the <strong>X-axis</strong>. You are given an integer array <code>prizePositions</code> that is <strong>sorted in non-decreasing order</strong>, where <code>prizePositions[i]</code> is the position of the <code>i<sup>th</sup></code> prize. There could be different prizes at the same position on the line. You are also given an integer <code>k</code>.</p>

<p>You are allowed to select two segments with integer endpoints. The length of each segment must be <code>k</code>. You will collect all prizes whose position falls within at least one of the two selected segments (including the endpoints of the segments). The two selected segments may intersect.</p>

<ul>
	<li>For example if <code>k = 2</code>, you can choose segments <code>[1, 3]</code> and <code>[2, 4]</code>, and you will win any prize <font face="monospace">i</font> that satisfies <code>1 &lt;= prizePositions[i] &lt;= 3</code> or <code>2 &lt;= prizePositions[i] &lt;= 4</code>.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> number of prizes you can win if you choose the two segments optimally</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> prizePositions = [1,1,2,2,3,3,5], k = 2
<strong>Output:</strong> 7
<strong>Explanation:</strong> In this example, you can win all 7 prizes by selecting two segments [1, 3] and [3, 5].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> prizePositions = [1,2,3,4], k = 0
<strong>Output:</strong> 2
<strong>Explanation:</strong> For this example, <strong>one choice</strong> for the segments is <code>[3, 3]</code> and <code>[4, 4],</code> and you will be able to get <code>2</code> prizes. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prizePositions.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= prizePositions[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>9</sup> </code></li>
	<li><code>prizePositions</code> is sorted in non-decreasing order.</li>
</ul>

<p>&nbsp;</p>
<style type="text/css">.spoilerbutton {display:block; border:dashed; padding: 0px 0px; margin:10px 0px; font-size:150%; font-weight: bold; color:#000000; background-color:cyan; outline:0; 
}
.spoiler {overflow:hidden;}
.spoiler > div {-webkit-transition: all 0s ease;-moz-transition: margin 0s ease;-o-transition: all 0s ease;transition: margin 0s ease;}
.spoilerbutton[value="Show Message"] + .spoiler > div {margin-top:-500%;}
.spoilerbutton[value="Hide Message"] + .spoiler {padding:5px;}
</style>

## Solutions

### Solution 1: Dynamic Programming + Binary Search

We define $f[i]$ as the maximum number of prizes that can be obtained by selecting a segment of length $k$ from the first $i$ prizes. Initially, $f[0] = 0$. We define the answer variable as $ans = 0$.

Next, we enumerate the position $x$ of each prize, and use binary search to find the leftmost prize index $j$ such that $prizePositions[j] \geq x - k$. At this point, we update the answer $ans = \max(ans, f[j] + i - j)$, and update $f[i] = \max(f[i - 1], i - j)$.

Finally, we return $ans$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Where $n$ is the number of prizes.

<!-- tabs:start -->

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
```

```ts
function maximizeWin(prizePositions: number[], k: number): number {
    const n = prizePositions.length;
    const f: number[] = Array(n + 1).fill(0);
    let ans = 0;
    const search = (x: number): number => {
        let left = 0;
        let right = n;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (prizePositions[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    };
    for (let i = 1; i <= n; ++i) {
        const x = prizePositions[i - 1];
        const j = search(x - k);
        ans = Math.max(ans, f[j] + i - j);
        f[i] = Math.max(f[i - 1], i - j);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
