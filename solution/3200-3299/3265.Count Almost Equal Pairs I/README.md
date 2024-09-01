---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3265.Count%20Almost%20Equal%20Pairs%20I/README.md
tags:
    - 数组
    - 哈希表
    - 计数
    - 枚举
    - 排序
---

<!-- problem:start -->

# [3265. 统计近似相等数对 I](https://leetcode.cn/problems/count-almost-equal-pairs-i)

[English Version](/solution/3200-3299/3265.Count%20Almost%20Equal%20Pairs%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>如果我们执行以下操作 <strong>至多一次</strong>&nbsp;可以让两个整数&nbsp;<code>x</code> 和&nbsp;<code>y</code>&nbsp;相等，那么我们称这个数对是 <strong>近似相等</strong>&nbsp;的：</p>

<ul>
	<li>选择&nbsp;<code>x</code> <strong>或者</strong>&nbsp;<code>y</code> &nbsp;之一，将这个数字中的两个数位交换。</li>
</ul>

<p>请你返回 <code>nums</code>&nbsp;中，下标 <code>i</code>&nbsp;和 <code>j</code>&nbsp;满足&nbsp;<code>i &lt; j</code>&nbsp;且&nbsp;<code>nums[i]</code> 和&nbsp;<code>nums[j]</code> <strong>近似相等</strong>&nbsp;的数对数目。</p>

<p><b>注意</b>&nbsp;，执行操作后一个整数可以有前导 0 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [3,12,30,17,21]</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p>近似相等数对包括：</p>

<ul>
	<li>3 和 30 。交换 30 中的数位 3 和 0 ，得到 3 。</li>
	<li>12 和 21 。交换12 中的数位 1 和 2 ，得到 21 。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,1,1,1,1]</span></p>

<p><span class="example-io"><b>输出：</b>10</span></p>

<p><strong>解释：</strong></p>

<p>数组中的任意两个元素都是近似相等的。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [123,231]</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<p>我们无法通过交换 123&nbsp;或者 231 中的两个数位得到另一个数。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 枚举

我们可以枚举每一个数，然后对于每一个数，我们可以枚举每一对不同的数位，然后交换这两个数位，得到一个新的数，记录到一个哈希表 $\textit{vis}$ 中，表示这个数至多进行一次交换后的所有可能的数。然后计算前面枚举过的数中有多少个数在哈希表 $\textit{vis}$ 中，累加到答案中。接下来，我们将当前枚举的数加入到哈希表 $\textit{cnt}$ 中，表示当前枚举的数的个数。

这样枚举，会少统计一些数对，比如 $[100, 1]$，因为 $100$ 交换后的数是 $1$，而此前枚举过数不包含 $1$，因此会少统计一些数对。我们只需要在枚举之前，将数组排序，即可解决这个问题。

时间复杂度 $O(n \times (\log n + \log^3 M))$，空间复杂度 $O(n + \log^2 M)$。其中 $n$ 是数组 $\textit{nums}$ 的长度，而 $M$ 是数组 $\textit{nums}$ 中的最大值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPairs(self, nums: List[int]) -> int:
        nums.sort()
        ans = 0
        cnt = defaultdict(int)
        for x in nums:
            vis = {x}
            s = list(str(x))
            for j in range(len(s)):
                for i in range(j):
                    s[i], s[j] = s[j], s[i]
                    vis.add(int("".join(s)))
                    s[i], s[j] = s[j], s[i]
            ans += sum(cnt[x] for x in vis)
            cnt[x] += 1
        return ans
```

#### Java

```java
class Solution {
    public int countPairs(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            Set<Integer> vis = new HashSet<>();
            vis.add(x);
            char[] s = String.valueOf(x).toCharArray();
            for (int j = 0; j < s.length; ++j) {
                for (int i = 0; i < j; ++i) {
                    swap(s, i, j);
                    vis.add(Integer.parseInt(String.valueOf(s)));
                    swap(s, i, j);
                }
            }
            for (int y : vis) {
                ans += cnt.getOrDefault(y, 0);
            }
            cnt.merge(x, 1, Integer::sum);
        }
        return ans;
    }

    private void swap(char[] s, int i, int j) {
        char t = s[i];
        s[i] = s[j];
        s[j] = t;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countPairs(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int ans = 0;
        unordered_map<int, int> cnt;

        for (int x : nums) {
            unordered_set<int> vis = {x};
            string s = to_string(x);

            for (int j = 0; j < s.length(); ++j) {
                for (int i = 0; i < j; ++i) {
                    swap(s[i], s[j]);
                    vis.insert(stoi(s));
                    swap(s[i], s[j]);
                }
            }

            for (int y : vis) {
                ans += cnt[y];
            }
            cnt[x]++;
        }

        return ans;
    }
};
```

#### Go

```go
func countPairs(nums []int) (ans int) {
	sort.Ints(nums)
	cnt := make(map[int]int)

	for _, x := range nums {
		vis := make(map[int]struct{})
		vis[x] = struct{}{}
		s := []rune(strconv.Itoa(x))

		for j := 0; j < len(s); j++ {
			for i := 0; i < j; i++ {
				s[i], s[j] = s[j], s[i]
				y, _ := strconv.Atoi(string(s))
				vis[y] = struct{}{}
				s[i], s[j] = s[j], s[i]
			}
		}

		for y := range vis {
			ans += cnt[y]
		}
		cnt[x]++
	}

	return
}
```

#### TypeScript

```ts
function countPairs(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let ans = 0;
    const cnt = new Map<number, number>();

    for (const x of nums) {
        const vis = new Set<number>();
        vis.add(x);
        const s = x.toString().split('');

        for (let j = 0; j < s.length; j++) {
            for (let i = 0; i < j; i++) {
                [s[i], s[j]] = [s[j], s[i]];
                vis.add(+s.join(''));
                [s[i], s[j]] = [s[j], s[i]];
            }
        }

        for (const y of vis) {
            ans += cnt.get(y) || 0;
        }
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
