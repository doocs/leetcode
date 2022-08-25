# [1452. People Whose List of Favorite Companies Is Not a Subset of Another List](https://leetcode.com/problems/people-whose-list-of-favorite-companies-is-not-a-subset-of-another-list)

[中文文档](/solution/1400-1499/1452.People%20Whose%20List%20of%20Favorite%20Companies%20Is%20Not%20a%20Subset%20of%20Another%20List/README.md)

## Description

<p>Given the array <code>favoriteCompanies</code> where <code>favoriteCompanies[i]</code> is the list of favorites companies for the <code>ith</code> person (<strong>indexed from 0</strong>).</p>

<p><em>Return the indices of people whose list of favorite companies is not a <strong>subset</strong> of any other list of favorites companies</em>. You must return the indices in increasing order.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> favoriteCompanies = [[&quot;leetcode&quot;,&quot;google&quot;,&quot;facebook&quot;],[&quot;google&quot;,&quot;microsoft&quot;],[&quot;google&quot;,&quot;facebook&quot;],[&quot;google&quot;],[&quot;amazon&quot;]]
<strong>Output:</strong> [0,1,4] 
<strong>Explanation:</strong> 
Person with index=2 has favoriteCompanies[2]=[&quot;google&quot;,&quot;facebook&quot;] which is a subset of favoriteCompanies[0]=[&quot;leetcode&quot;,&quot;google&quot;,&quot;facebook&quot;] corresponding to the person with index 0. 
Person with index=3 has favoriteCompanies[3]=[&quot;google&quot;] which is a subset of favoriteCompanies[0]=[&quot;leetcode&quot;,&quot;google&quot;,&quot;facebook&quot;] and favoriteCompanies[1]=[&quot;google&quot;,&quot;microsoft&quot;]. 
Other lists of favorite companies are not a subset of another list, therefore, the answer is [0,1,4].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> favoriteCompanies = [[&quot;leetcode&quot;,&quot;google&quot;,&quot;facebook&quot;],[&quot;leetcode&quot;,&quot;amazon&quot;],[&quot;facebook&quot;,&quot;google&quot;]]
<strong>Output:</strong> [0,1] 
<strong>Explanation:</strong> In this case favoriteCompanies[2]=[&quot;facebook&quot;,&quot;google&quot;] is a subset of favoriteCompanies[0]=[&quot;leetcode&quot;,&quot;google&quot;,&quot;facebook&quot;], therefore, the answer is [0,1].
</pre>

<p><strong>Example 3:</strong></p>

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

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def peopleIndexes(self, favoriteCompanies: List[List[str]]) -> List[int]:
        d = {}
        idx = 0
        t = []
        for v in favoriteCompanies:
            for c in v:
                if c not in d:
                    d[c] = idx
                    idx += 1
            t.append({d[c] for c in v})
        ans = []
        for i, nums1 in enumerate(t):
            ok = True
            for j, nums2 in enumerate(t):
                if i == j:
                    continue
                if not (nums1 - nums2):
                    ok = False
                    break
            if ok:
                ans.append(i)
        return ans
```

### **Java**

```java
class Solution {
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        Map<String, Integer> d = new HashMap<>();
        int idx = 0;
        int n = favoriteCompanies.size();
        Set<Integer>[] t = new Set[n];
        for (int i = 0; i < n; ++i) {
            var v = favoriteCompanies.get(i);
            for (var c : v) {
                if (!d.containsKey(c)) {
                    d.put(c, idx++);
                }
            }
            Set<Integer> s = new HashSet<>();
            for (var c : v) {
                s.add(d.get(c));
            }
            t[i] = s;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            boolean ok = true;
            for (int j = 0; j < n; ++j) {
                if (i != j) {
                    if (t[j].containsAll(t[i])) {
                        ok = false;
                        break;
                    }
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

### **C++**

```cpp
class Solution {
public:
    vector<int> peopleIndexes(vector<vector<string>>& favoriteCompanies) {
        unordered_map<string, int> d;
        int idx = 0, n = favoriteCompanies.size();
        vector<unordered_set<int>> t(n);
        for (int i = 0; i < n; ++i) {
            auto v = favoriteCompanies[i];
            for (auto& c : v) {
                if (!d.count(c)) {
                    d[c] = idx++;
                }
            }
            unordered_set<int> s;
            for (auto& c : v) {
                s.insert(d[c]);
            }
            t[i] = s;
        }
        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            bool ok = true;
            for (int j = 0; j < n; ++j) {
                if (i == j) continue;
                if (check(t[i], t[j])) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans.push_back(i);
            }
        }
        return ans;
    }

    bool check(unordered_set<int>& nums1, unordered_set<int>& nums2) {
        for (int v : nums1) {
            if (!nums2.count(v)) {
                return false;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func peopleIndexes(favoriteCompanies [][]string) []int {
	d := map[string]int{}
	idx, n := 0, len(favoriteCompanies)
	t := make([]map[int]bool, n)
	for i, v := range favoriteCompanies {
		for _, c := range v {
			if _, ok := d[c]; !ok {
				d[c] = idx
				idx++
			}
		}
		s := map[int]bool{}
		for _, c := range v {
			s[d[c]] = true
		}
		t[i] = s
	}
	ans := []int{}
	check := func(nums1, nums2 map[int]bool) bool {
		for v, _ := range nums1 {
			if _, ok := nums2[v]; !ok {
				return false
			}
		}
		return true
	}
	for i := 0; i < n; i++ {
		ok := true
		for j := 0; j < n; j++ {
			if i == j {
				continue
			}
			if check(t[i], t[j]) {
				ok = false
				break
			}
		}
		if ok {
			ans = append(ans, i)
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
