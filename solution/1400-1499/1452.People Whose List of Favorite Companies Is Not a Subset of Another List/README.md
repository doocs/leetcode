# [1452. 收藏清单](https://leetcode.cn/problems/people-whose-list-of-favorite-companies-is-not-a-subset-of-another-list)

[English Version](/solution/1400-1499/1452.People%20Whose%20List%20of%20Favorite%20Companies%20Is%20Not%20a%20Subset%20of%20Another%20List/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

将每个 `company` 字符串列表都转换为一个整数类型的集合。然后遍历每个集合，判断其是否是其他集合的子集，如果不是，则将其下标加入结果集。

时间复杂度 $O(n^2 \times m)$，其中 $n$ 为 `favoriteCompanies` 的长度，$m$ 为 `favoriteCompanies[i]` 的最大长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
