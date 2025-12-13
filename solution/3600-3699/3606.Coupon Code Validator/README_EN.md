---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3606.Coupon%20Code%20Validator/README_EN.md
rating: 1312
source: Weekly Contest 457 Q1
tags:
    - Array
    - Hash Table
    - String
    - Sorting
---

<!-- problem:start -->

# [3606. Coupon Code Validator](https://leetcode.com/problems/coupon-code-validator)

[中文文档](/solution/3600-3699/3606.Coupon%20Code%20Validator/README.md)

## Description

<!-- description:start -->

<p>You are given three arrays of length <code>n</code> that describe the properties of <code>n</code> coupons: <code>code</code>, <code>businessLine</code>, and <code>isActive</code>. The <code>i<sup>th</sup> </code>coupon has:</p>

<ul>
	<li><code>code[i]</code>: a <strong>string</strong> representing the coupon identifier.</li>
	<li><code>businessLine[i]</code>: a <strong>string</strong> denoting the business category of the coupon.</li>
	<li><code>isActive[i]</code>: a <strong>boolean</strong> indicating whether the coupon is currently active.</li>
</ul>

<p>A coupon is considered <strong>valid</strong> if all of the following conditions hold:</p>

<ol>
	<li><code>code[i]</code> is non-empty and consists only of alphanumeric characters (a-z, A-Z, 0-9) and underscores (<code>_</code>).</li>
	<li><code>businessLine[i]</code> is one of the following four categories: <code>&quot;electronics&quot;</code>, <code>&quot;grocery&quot;</code>, <code>&quot;pharmacy&quot;</code>, <code>&quot;restaurant&quot;</code>.</li>
	<li><code>isActive[i]</code> is <strong>true</strong>.</li>
</ol>

<p>Return an array of the <strong>codes</strong> of all valid coupons, <strong>sorted</strong> first by their <strong>businessLine</strong> in the order: <code>&quot;electronics&quot;</code>, <code>&quot;grocery&quot;</code>, <code>&quot;pharmacy&quot;, &quot;restaurant&quot;</code>, and then by <strong>code</strong> in lexicographical (ascending) order within each category.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">code = [&quot;SAVE20&quot;,&quot;&quot;,&quot;PHARMA5&quot;,&quot;SAVE@20&quot;], businessLine = [&quot;restaurant&quot;,&quot;grocery&quot;,&quot;pharmacy&quot;,&quot;restaurant&quot;], isActive = [true,true,true,true]</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;PHARMA5&quot;,&quot;SAVE20&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>First coupon is valid.</li>
	<li>Second coupon has empty code (invalid).</li>
	<li>Third coupon is valid.</li>
	<li>Fourth coupon has special character <code>@</code> (invalid).</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">code = [&quot;GROCERY15&quot;,&quot;ELECTRONICS_50&quot;,&quot;DISCOUNT10&quot;], businessLine = [&quot;grocery&quot;,&quot;electronics&quot;,&quot;invalid&quot;], isActive = [false,true,true]</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;ELECTRONICS_50&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>First coupon is inactive (invalid).</li>
	<li>Second coupon is valid.</li>
	<li>Third coupon has invalid business line (invalid).</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == code.length == businessLine.length == isActive.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= code[i].length, businessLine[i].length &lt;= 100</code></li>
	<li><code>code[i]</code> and <code>businessLine[i]</code> consist of printable ASCII characters.</li>
	<li><code>isActive[i]</code> is either <code>true</code> or <code>false</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can directly simulate the conditions described in the problem to filter out valid coupons. The specific steps are as follows:

1. **Check Identifier**: For each coupon's identifier, check whether it is non-empty and contains only letters, digits, and underscores.
2. **Check Business Category**: Check whether each coupon's business category belongs to one of the four valid categories.
3. **Check Activation Status**: Check whether each coupon is active.
4. **Collect Valid Coupons**: Collect the ids of all coupons that satisfy the above conditions.
5. **Sort**: Sort the valid coupons by business category and identifier.
6. **Return Result**: Return the list of identifiers of the sorted valid coupons.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$, where $n$ is the number of coupons.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def validateCoupons(
        self, code: List[str], businessLine: List[str], isActive: List[bool]
    ) -> List[str]:
        def check(s: str) -> bool:
            if not s:
                return False
            for c in s:
                if not (c.isalpha() or c.isdigit() or c == "_"):
                    return False
            return True

        idx = []
        bs = {"electronics", "grocery", "pharmacy", "restaurant"}
        for i, (c, b, a) in enumerate(zip(code, businessLine, isActive)):
            if a and b in bs and check(c):
                idx.append(i)
        idx.sort(key=lambda i: (businessLine[i], code[i]))
        return [code[i] for i in idx]
```

#### Java

```java
class Solution {
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        List<Integer> idx = new ArrayList<>();
        Set<String> bs
            = new HashSet<>(Arrays.asList("electronics", "grocery", "pharmacy", "restaurant"));

        for (int i = 0; i < code.length; i++) {
            if (isActive[i] && bs.contains(businessLine[i]) && check(code[i])) {
                idx.add(i);
            }
        }

        idx.sort((i, j) -> {
            int cmp = businessLine[i].compareTo(businessLine[j]);
            if (cmp != 0) {
                return cmp;
            }
            return code[i].compareTo(code[j]);
        });

        List<String> ans = new ArrayList<>();
        for (int i : idx) {
            ans.add(code[i]);
        }
        return ans;
    }

    private boolean check(String s) {
        if (s.isEmpty()) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> validateCoupons(vector<string>& code, vector<string>& businessLine, vector<bool>& isActive) {
        vector<int> idx;
        unordered_set<string> bs = {"electronics", "grocery", "pharmacy", "restaurant"};

        for (int i = 0; i < code.size(); ++i) {
            const string& c = code[i];
            const string& b = businessLine[i];
            bool a = isActive[i];
            if (a && bs.count(b) && check(c)) {
                idx.push_back(i);
            }
        }

        sort(idx.begin(), idx.end(), [&](int i, int j) {
            if (businessLine[i] != businessLine[j]) return businessLine[i] < businessLine[j];
            return code[i] < code[j];
        });

        vector<string> ans;
        for (int i : idx) {
            ans.push_back(code[i]);
        }
        return ans;
    }

private:
    bool check(const string& s) {
        if (s.empty()) return false;
        for (char c : s) {
            if (!isalnum(c) && c != '_') {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func validateCoupons(code []string, businessLine []string, isActive []bool) []string {
	idx := []int{}
	bs := map[string]struct{}{
		"electronics": {},
		"grocery":     {},
		"pharmacy":    {},
		"restaurant":  {},
	}

	check := func(s string) bool {
		if len(s) == 0 {
			return false
		}
		for _, c := range s {
			if !unicode.IsLetter(c) && !unicode.IsDigit(c) && c != '_' {
				return false
			}
		}
		return true
	}

	for i := range code {
		if isActive[i] {
			if _, ok := bs[businessLine[i]]; ok && check(code[i]) {
				idx = append(idx, i)
			}
		}
	}

	sort.Slice(idx, func(i, j int) bool {
		if businessLine[idx[i]] != businessLine[idx[j]] {
			return businessLine[idx[i]] < businessLine[idx[j]]
		}
		return code[idx[i]] < code[idx[j]]
	})

	ans := make([]string, 0, len(idx))
	for _, i := range idx {
		ans = append(ans, code[i])
	}
	return ans
}
```

#### TypeScript

```ts
function validateCoupons(code: string[], businessLine: string[], isActive: boolean[]): string[] {
    const idx: number[] = [];
    const bs = new Set(['electronics', 'grocery', 'pharmacy', 'restaurant']);

    const check = (s: string): boolean => {
        if (s.length === 0) return false;
        for (let i = 0; i < s.length; i++) {
            const c = s[i];
            if (!/[a-zA-Z0-9_]/.test(c)) {
                return false;
            }
        }
        return true;
    };

    for (let i = 0; i < code.length; i++) {
        if (isActive[i] && bs.has(businessLine[i]) && check(code[i])) {
            idx.push(i);
        }
    }

    idx.sort((i, j) => {
        if (businessLine[i] !== businessLine[j]) {
            return businessLine[i] < businessLine[j] ? -1 : 1;
        }
        return code[i] < code[j] ? -1 : 1;
    });

    return idx.map(i => code[i]);
}
```

#### Rust

```rust
use std::collections::HashSet;

impl Solution {
    pub fn validate_coupons(
        code: Vec<String>,
        business_line: Vec<String>,
        is_active: Vec<bool>,
    ) -> Vec<String> {
        fn check(s: &str) -> bool {
            if s.is_empty() {
                return false;
            }
            s.chars()
                .all(|c| c.is_ascii_alphanumeric() || c == '_')
        }

        let bs: HashSet<&str> =
            ["electronics", "grocery", "pharmacy", "restaurant"]
                .iter()
                .copied()
                .collect();

        let mut idx: Vec<usize> = Vec::new();
        for i in 0..code.len() {
            if is_active[i] && bs.contains(business_line[i].as_str()) && check(&code[i]) {
                idx.push(i);
            }
        }

        idx.sort_by(|&i, &j| {
            let cmp = business_line[i].cmp(&business_line[j]);
            if cmp == std::cmp::Ordering::Equal {
                code[i].cmp(&code[j])
            } else {
                cmp
            }
        });

        idx.into_iter().map(|i| code[i].clone()).collect()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
