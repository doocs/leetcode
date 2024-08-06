---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1452.People%20Whose%20List%20of%20Favorite%20Companies%20Is%20Not%20a%20Subset%20of%20Another%20List/README_EN.md
rating: 1562
source: Weekly Contest 189 Q3
tags:
    - Array
    - Hash Table
    - String
---

<!-- problem:start -->

# [1452. People Whose List of Favorite Companies Is Not a Subset of Another List](https://leetcode.com/problems/people-whose-list-of-favorite-companies-is-not-a-subset-of-another-list)

[中文文档](/solution/1400-1499/1452.People%20Whose%20List%20of%20Favorite%20Companies%20Is%20Not%20a%20Subset%20of%20Another%20List/README.md)

## Description

<!-- description:start -->

<p>Given the array <code>favoriteCompanies</code> where <code>favoriteCompanies[i]</code> is the list of favorites companies for the <code>ith</code> person (<strong>indexed from 0</strong>).</p>

<p><em>Return the indices of people whose list of favorite companies is not a <strong>subset</strong> of any other list of favorites companies</em>. You must return the indices in increasing order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> favoriteCompanies = [[&quot;leetcode&quot;,&quot;google&quot;,&quot;facebook&quot;],[&quot;google&quot;,&quot;microsoft&quot;],[&quot;google&quot;,&quot;facebook&quot;],[&quot;google&quot;],[&quot;amazon&quot;]]
<strong>Output:</strong> [0,1,4] 
<strong>Explanation:</strong> 
Person with index=2 has favoriteCompanies[2]=[&quot;google&quot;,&quot;facebook&quot;] which is a subset of favoriteCompanies[0]=[&quot;leetcode&quot;,&quot;google&quot;,&quot;facebook&quot;] corresponding to the person with index 0. 
Person with index=3 has favoriteCompanies[3]=[&quot;google&quot;] which is a subset of favoriteCompanies[0]=[&quot;leetcode&quot;,&quot;google&quot;,&quot;facebook&quot;] and favoriteCompanies[1]=[&quot;google&quot;,&quot;microsoft&quot;]. 
Other lists of favorite companies are not a subset of another list, therefore, the answer is [0,1,4].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> favoriteCompanies = [[&quot;leetcode&quot;,&quot;google&quot;,&quot;facebook&quot;],[&quot;leetcode&quot;,&quot;amazon&quot;],[&quot;facebook&quot;,&quot;google&quot;]]
<strong>Output:</strong> [0,1] 
<strong>Explanation:</strong> In this case favoriteCompanies[2]=[&quot;facebook&quot;,&quot;google&quot;] is a subset of favoriteCompanies[0]=[&quot;leetcode&quot;,&quot;google&quot;,&quot;facebook&quot;], therefore, the answer is [0,1].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> favoriteCompanies = [[&quot;leetcode&quot;],[&quot;google&quot;],[&quot;facebook&quot;],[&quot;amazon&quot;]]
<strong>Output:</strong> [0,1,2,3]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= favoriteCompanies.length &lt;= 100</code></li>
	<li><code>1 &lt;= favoriteCompanies[i].length &lt;= 500</code></li>
	<li><code>1 &lt;= favoriteCompanies[i][j].length &lt;= 20</code></li>
	<li>All strings in <code>favoriteCompanies[i]</code> are <strong>distinct</strong>.</li>
	<li>All lists of favorite companies are <strong>distinct</strong>, that is, If we sort alphabetically each list then <code>favoriteCompanies[i] != favoriteCompanies[j].</code></li>
	<li>All strings consist of lowercase English letters only.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We can map each company to a unique integer. Then, for each person, we convert their favorite companies into a set of integers. Finally, we check if the favorite companies of one person are a subset of another person's favorite companies.

The time complexity is $(n \times m \times k + n^2 \times m)$, and the space complexity is $O(n \times m)$. Here, $n$ and $m$ are the lengths of `favoriteCompanies` and the average length of each company's list, respectively, and $k$ is the average length of each company.

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
