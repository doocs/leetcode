---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3267.Count%20Almost%20Equal%20Pairs%20II/README.md
rating: 2545
source: 第 412 场周赛 Q4
tags:
    - 数组
    - 哈希表
    - 计数
    - 枚举
    - 排序
---

<!-- problem:start -->

# [3267. 统计近似相等数对 II](https://leetcode.cn/problems/count-almost-equal-pairs-ii)

[English Version](/solution/3200-3299/3267.Count%20Almost%20Equal%20Pairs%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p><strong>注意：</strong>在这个问题中，操作次数增加为至多&nbsp;<strong>两次</strong>&nbsp;。</p>

<p>给你一个正整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>如果我们执行以下操作 <strong>至多<u>两次</u></strong>&nbsp;可以让两个整数&nbsp;<code>x</code> 和&nbsp;<code>y</code>&nbsp;相等，那么我们称这个数对是 <strong>近似相等</strong>&nbsp;的：</p>

<ul>
	<li>选择&nbsp;<code>x</code> <strong>或者</strong>&nbsp;<code>y</code> &nbsp;之一，将这个数字中的两个数位交换。</li>
</ul>

<p>请你返回 <code>nums</code>&nbsp;中，下标 <code>i</code>&nbsp;和 <code>j</code>&nbsp;满足&nbsp;<code>i &lt; j</code>&nbsp;且&nbsp;<code>nums[i]</code> 和&nbsp;<code>nums[j]</code> <strong>近似相等</strong>&nbsp;的数对数目。</p>

<p><b>注意</b>&nbsp;，执行操作后得到的整数可以有前导 0 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1023,2310,2130,213]</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p>近似相等数对包括：</p>

<ul>
	<li>1023 和 2310 。交换 1023 中数位 1 和 2 ，然后交换数位 0 和 3 ，得到 2310 。</li>
	<li>1023 和 213 。交换 1023 中数位 1 和 0 ，然后交换数位 1 和 2 ，得到 0213 ，也就是 213 。</li>
	<li>2310 和 213 。交换 2310 中数位 2 和 0 ，然后交换数位 3 和 2 ，得到 0213 ，也就是 213 。</li>
	<li>2310 和 2130 。交换 2310 中数位 3 和 1 ，得到 2130 。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,10,100]</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>

<p>近似相等数对包括：</p>

<ul>
	<li>1 和 10 。交换 10 中数位 1 和 0&nbsp;，得到 01 ，也就是 1&nbsp;。</li>
	<li>1 和 100 。交换 100 中数位 1 和从左往右的第二个 0 ，得到 001 ，也就是 1 。</li>
	<li>10 和 100 。交换 100 中数位 1 和从左往右的第一个 0 ，得到 010 ，也就是 10 。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 5000</code></li>
	<li><code>1 &lt;= nums[i] &lt;&nbsp;10<sup>7</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 枚举

我们可以枚举每一个数，然后对于每一个数，我们可以枚举每一对不同的数位，然后交换这两个数位，得到一个新的数，记录到一个哈希表 $\textit{vis}$ 中，表示这个数至多进行一次交换后的所有可能的数，然后继续枚举每一对不同的数位，交换这两个数位，得到一个新的数，记录到哈希表 $\textit{vis}$ 中，表示这个数至多进行两次交换后的所有可能的数。

这样枚举，会少统计一些数对，比如 $[100, 1]$，因为 $100$ 交换后的数是 $1$，而此前枚举过数不包含 $1$，因此会少统计一些数对。我们只需要在枚举之前，将数组排序，即可解决这个问题。

时间复杂度 $O(n \times (\log n + \log^5 M))$，空间复杂度 $O(n + \log^4 M)$。其中 $n$ 是数组 $\textit{nums}$ 的长度，而 $M$ 是数组 $\textit{nums}$ 中的最大值。

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
            m = len(s)
            for j in range(m):
                for i in range(j):
                    s[i], s[j] = s[j], s[i]
                    vis.add(int("".join(s)))
                    for q in range(i + 1, m):
                        for p in range(i + 1, q):
                            s[p], s[q] = s[q], s[p]
                            vis.add(int("".join(s)))
                            s[p], s[q] = s[q], s[p]
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
                    for (int q = i; q < s.length; ++q) {
                        for (int p = i; p < q; ++p) {
                            swap(s, p, q);
                            vis.add(Integer.parseInt(String.valueOf(s)));
                            swap(s, p, q);
                        }
                    }
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
                    for (int q = i + 1; q < s.length(); ++q) {
                        for (int p = i + 1; p < q; ++p) {
                            swap(s[p], s[q]);
                            vis.insert(stoi(s));
                            swap(s[p], s[q]);
                        }
                    }
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
				for q := i + 1; q < len(s); q++ {
					for p := i + 1; p < q; p++ {
						s[p], s[q] = s[q], s[p]
						z, _ := strconv.Atoi(string(s))
						vis[z] = struct{}{}
						s[p], s[q] = s[q], s[p]
					}
				}
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
                for (let q = i + 1; q < s.length; ++q) {
                    for (let p = i + 1; p < q; ++p) {
                        [s[p], s[q]] = [s[q], s[p]];
                        vis.add(+s.join(''));
                        [s[p], s[q]] = [s[q], s[p]];
                    }
                }
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
