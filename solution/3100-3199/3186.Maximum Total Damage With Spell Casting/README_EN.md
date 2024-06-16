---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3186.Maximum%20Total%20Damage%20With%20Spell%20Casting/README_EN.md
---

<!-- problem:start -->

# [3186. Maximum Total Damage With Spell Casting](https://leetcode.com/problems/maximum-total-damage-with-spell-casting)

[中文文档](/solution/3100-3199/3186.Maximum%20Total%20Damage%20With%20Spell%20Casting/README.md)

## Description

<!-- description:start -->

<p>A magician has various spells.</p>

<p>You are given an array <code>power</code>, where each element represents the damage of a spell. Multiple spells can have the same damage value.</p>

<p>It is a known fact that if a magician decides to cast a spell with a damage of <code>power[i]</code>, they <strong>cannot</strong> cast any spell with a damage of <code>power[i] - 2</code>, <code>power[i] - 1</code>, <code>power[i] + 1</code>, or <code>power[i] + 2</code>.</p>

<p>Each spell can be cast <strong>only once</strong>.</p>

<p>Return the <strong>maximum</strong> possible <em>total damage</em> that a magician can cast.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">power = [1,1,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>The maximum possible damage of 6 is produced by casting spells 0, 1, 3 with damage 1, 1, 4.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">power = [7,1,6,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">13</span></p>

<p><strong>Explanation:</strong></p>

<p>The maximum possible damage of 13 is produced by casting spells 1, 2, 3 with damage 1, 6, 6.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= power.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= power[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search + Memoization

We can first sort the array $\text{power}$, use a hash table $\text{cnt}$ to record the occurrence count of each damage value, and then iterate through the array $\text{power}$. For each damage value $x$, we can determine the index of the next damage value that can be used when using a spell with damage value $x$, which is the index of the first damage value greater than $x + 2$. We can use binary search to find this index and record it in the array $\text{nxt}$.

Next, we define a function $\text{dfs}$ to calculate the maximum damage value that can be obtained starting from the $i$-th damage value.

In the $\text{dfs}$ function, we can choose to skip the current damage value, so we can skip all the same damage values of the current one and directly jump to $i + \text{cnt}[x]$, obtaining a damage value of $\text{dfs}(i + \text{cnt}[x])$; or we can choose to use the current damage value, so we can use all the same damage values of the current one and then jump to the index of the next damage value, obtaining a damage value of $x \times \text{cnt}[x] + \text{dfs}(\text{nxt}[i])$, where $\text{nxt}[i]$ represents the index of the first damage value greater than $x + 2$. We take the maximum of these two cases as the return value of the function.

To avoid repeated calculations, we can use memoization, storing the results that have already been calculated in an array $\text{f}$. Thus, when calculating $\text{dfs}(i)$, if $\text{f}[i]$ is not $0$, we directly return $\text{f}[i]$.

The answer is $\text{dfs}(0)$.

The time complexity is $O(n \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\text{power}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumTotalDamage(self, power: List[int]) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= n:
                return 0
            a = dfs(i + cnt[power[i]])
            b = power[i] * cnt[power[i]] + dfs(nxt[i])
            return max(a, b)

        n = len(power)
        cnt = Counter(power)
        power.sort()
        nxt = [bisect_right(power, x + 2, lo=i + 1) for i, x in enumerate(power)]
        return dfs(0)
```

#### Java

```java
class Solution {
    private Long[] f;
    private int[] power;
    private Map<Integer, Integer> cnt;
    private int[] nxt;
    private int n;

    public long maximumTotalDamage(int[] power) {
        Arrays.sort(power);
        this.power = power;
        n = power.length;
        f = new Long[n];
        cnt = new HashMap<>(n);
        nxt = new int[n];
        for (int i = 0; i < n; ++i) {
            cnt.merge(power[i], 1, Integer::sum);
            int l = Arrays.binarySearch(power, power[i] + 3);
            l = l < 0 ? -l - 1 : l;
            nxt[i] = l;
        }
        return dfs(0);
    }

    private long dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        long a = dfs(i + cnt.get(power[i]));
        long b = 1L * power[i] * cnt.get(power[i]) + dfs(nxt[i]);
        return f[i] = Math.max(a, b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumTotalDamage(vector<int>& power) {
        sort(power.begin(), power.end());
        this->power = power;
        n = power.size();
        f.resize(n);
        nxt.resize(n);
        for (int i = 0; i < n; ++i) {
            cnt[power[i]]++;
            nxt[i] = upper_bound(power.begin() + i + 1, power.end(), power[i] + 2) - power.begin();
        }
        return dfs(0);
    }

private:
    unordered_map<int, int> cnt;
    vector<long long> f;
    vector<int> power;
    vector<int> nxt;
    int n;

    long long dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i]) {
            return f[i];
        }
        long long a = dfs(i + cnt[power[i]]);
        long long b = 1LL * power[i] * cnt[power[i]] + dfs(nxt[i]);
        return f[i] = max(a, b);
    }
};
```

#### Go

```go
func maximumTotalDamage(power []int) int64 {
	n := len(power)
	sort.Ints(power)
	cnt := map[int]int{}
	nxt := make([]int, n)
	f := make([]int64, n)
	for i, x := range power {
		cnt[x]++
		nxt[i] = sort.SearchInts(power, x+3)
	}
	var dfs func(int) int64
	dfs = func(i int) int64 {
		if i >= n {
			return 0
		}
		if f[i] != 0 {
			return f[i]
		}
		a := dfs(i + cnt[power[i]])
		b := int64(power[i]*cnt[power[i]]) + dfs(nxt[i])
		f[i] = max(a, b)
		return f[i]
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function maximumTotalDamage(power: number[]): number {
    const n = power.length;
    power.sort((a, b) => a - b);
    const f: number[] = Array(n).fill(0);
    const cnt: Record<number, number> = {};
    const nxt: number[] = Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        cnt[power[i]] = (cnt[power[i]] || 0) + 1;
        let [l, r] = [i + 1, n];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (power[mid] > power[i] + 2) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        nxt[i] = l;
    }
    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i]) {
            return f[i];
        }
        const a = dfs(i + cnt[power[i]]);
        const b = power[i] * cnt[power[i]] + dfs(nxt[i]);
        return (f[i] = Math.max(a, b));
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
