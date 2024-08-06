---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1452.People%20Whose%20List%20of%20Favorite%20Companies%20Is%20Not%20a%20Subset%20of%20Another%20List/README.md
rating: 1562
source: 第 189 场周赛 Q3
tags:
    - 数组
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [1452. 收藏清单](https://leetcode.cn/problems/people-whose-list-of-favorite-companies-is-not-a-subset-of-another-list)

[English Version](/solution/1400-1499/1452.People%20Whose%20List%20of%20Favorite%20Companies%20Is%20Not%20a%20Subset%20of%20Another%20List/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组 <code>favoriteCompanies</code> ，其中 <code>favoriteCompanies[i]</code> 是第 <code>i</code> 名用户收藏的公司清单（<strong>下标从 0 开始</strong>）。</p>

<p>请找出不是其他任何人收藏的公司清单的子集的收藏清单，并返回该清单下标<em>。</em>下标需要按升序排列<em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>favoriteCompanies = [[&quot;leetcode&quot;,&quot;google&quot;,&quot;facebook&quot;],[&quot;google&quot;,&quot;microsoft&quot;],[&quot;google&quot;,&quot;facebook&quot;],[&quot;google&quot;],[&quot;amazon&quot;]]
<strong>输出：</strong>[0,1,4] 
<strong>解释：</strong>
favoriteCompanies[2]=[&quot;google&quot;,&quot;facebook&quot;] 是 favoriteCompanies[0]=[&quot;leetcode&quot;,&quot;google&quot;,&quot;facebook&quot;] 的子集。
favoriteCompanies[3]=[&quot;google&quot;] 是 favoriteCompanies[0]=[&quot;leetcode&quot;,&quot;google&quot;,&quot;facebook&quot;] 和 favoriteCompanies[1]=[&quot;google&quot;,&quot;microsoft&quot;] 的子集。
其余的收藏清单均不是其他任何人收藏的公司清单的子集，因此，答案为 [0,1,4] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>favoriteCompanies = [[&quot;leetcode&quot;,&quot;google&quot;,&quot;facebook&quot;],[&quot;leetcode&quot;,&quot;amazon&quot;],[&quot;facebook&quot;,&quot;google&quot;]]
<strong>输出：</strong>[0,1] 
<strong>解释：</strong>favoriteCompanies[2]=[&quot;facebook&quot;,&quot;google&quot;] 是 favoriteCompanies[0]=[&quot;leetcode&quot;,&quot;google&quot;,&quot;facebook&quot;] 的子集，因此，答案为 [0,1] 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>favoriteCompanies = [[&quot;leetcode&quot;],[&quot;google&quot;],[&quot;facebook&quot;],[&quot;amazon&quot;]]
<strong>输出：</strong>[0,1,2,3]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;favoriteCompanies.length &lt;= 100</code></li>
	<li><code>1 &lt;=&nbsp;favoriteCompanies[i].length &lt;= 500</code></li>
	<li><code>1 &lt;=&nbsp;favoriteCompanies[i][j].length &lt;= 20</code></li>
	<li><code>favoriteCompanies[i]</code> 中的所有字符串 <strong>各不相同</strong> 。</li>
	<li>用户收藏的公司清单也 <strong>各不相同</strong> ，也就是说，即便我们按字母顺序排序每个清单， <code>favoriteCompanies[i] != favoriteCompanies[j] </code>仍然成立。</li>
	<li>所有字符串仅包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们可以将每个公司映射到一个唯一的整数，然后对于每个人，我们将他们收藏的公司转换为整数集合，最后判断是否存在一个人的收藏公司是另一个人的子集。

时间复杂度 $(n \times m \times k + n^2 \times m)$，空间复杂度 $O(n \times m)$。其中 $n$ 和 $m$ 分别是 `favoriteCompanies` 的长度和每个公司清单的平均长度，而 $k$ 是每个公司的平均长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def peopleIndexes(self, favoriteCompanies: List[List[str]]) -> List[int]:
        idx = 0
        d = {}
        n = len(favoriteCompanies)
        nums = [set() for _ in range(n)]
        for i, ss in enumerate(favoriteCompanies):
            for s in ss:
                if s not in d:
                    d[s] = idx
                    idx += 1
                nums[i].add(d[s])
        ans = []
        for i in range(n):
            if not any(i != j and (nums[i] & nums[j]) == nums[i] for j in range(n)):
                ans.append(i)
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        int n = favoriteCompanies.size();
        Map<String, Integer> d = new HashMap<>();
        int idx = 0;
        Set<Integer>[] nums = new Set[n];
        Arrays.setAll(nums, i -> new HashSet<>());
        for (int i = 0; i < n; ++i) {
            var ss = favoriteCompanies.get(i);
            for (var s : ss) {
                if (!d.containsKey(s)) {
                    d.put(s, idx++);
                }
                nums[i].add(d.get(s));
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            boolean ok = true;
            for (int j = 0; j < n && ok; ++j) {
                if (i != j && nums[j].containsAll(nums[i])) {
                    ok = false;
                }
            }
            if (ok) {
                ans.add(i);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> peopleIndexes(vector<vector<string>>& favoriteCompanies) {
        int n = favoriteCompanies.size();
        unordered_map<string, int> d;
        int idx = 0;
        vector<unordered_set<int>> nums(n);

        for (int i = 0; i < n; ++i) {
            for (const auto& s : favoriteCompanies[i]) {
                if (!d.contains(s)) {
                    d[s] = idx++;
                }
                nums[i].insert(d[s]);
            }
        }

        auto check = [](const unordered_set<int>& a, const unordered_set<int>& b) {
            for (int x : a) {
                if (!b.contains(x)) {
                    return false;
                }
            }
            return true;
        };

        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            bool ok = true;
            for (int j = 0; j < n && ok; ++j) {
                if (i != j && check(nums[i], nums[j])) {
                    ok = false;
                }
            }
            if (ok) {
                ans.push_back(i);
            }
        }

        return ans;
    }
};
```

#### Go

```go
func peopleIndexes(favoriteCompanies [][]string) (ans []int) {
	n := len(favoriteCompanies)
	d := make(map[string]int)
	idx := 0
	nums := make([]map[int]struct{}, n)

	for i := 0; i < n; i++ {
		nums[i] = make(map[int]struct{})
		for _, s := range favoriteCompanies[i] {
			if _, ok := d[s]; !ok {
				d[s] = idx
				idx++
			}
			nums[i][d[s]] = struct{}{}
		}
	}

	check := func(a, b map[int]struct{}) bool {
		for x := range a {
			if _, ok := b[x]; !ok {
				return false
			}
		}
		return true
	}
	for i := 0; i < n; i++ {
		ok := true
		for j := 0; j < n && ok; j++ {
			if i != j && check(nums[i], nums[j]) {
				ok = false
			}
		}
		if ok {
			ans = append(ans, i)
		}
	}

	return
}
```

#### TypeScript

```ts
function peopleIndexes(favoriteCompanies: string[][]): number[] {
    const n = favoriteCompanies.length;
    const d: Map<string, number> = new Map();
    let idx = 0;
    const nums: Set<number>[] = Array.from({ length: n }, () => new Set<number>());

    for (let i = 0; i < n; i++) {
        for (const s of favoriteCompanies[i]) {
            if (!d.has(s)) {
                d.set(s, idx++);
            }
            nums[i].add(d.get(s)!);
        }
    }

    const check = (a: Set<number>, b: Set<number>): boolean => {
        for (const x of a) {
            if (!b.has(x)) {
                return false;
            }
        }
        return true;
    };

    const ans: number[] = [];
    for (let i = 0; i < n; i++) {
        let ok = true;
        for (let j = 0; j < n && ok; j++) {
            if (i !== j && check(nums[i], nums[j])) {
                ok = false;
            }
        }
        if (ok) {
            ans.push(i);
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
