---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3606.Coupon%20Code%20Validator/README.md
rating: 1312
source: 第 457 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 字符串
    - 排序
---

<!-- problem:start -->

# [3606. 优惠券校验器](https://leetcode.cn/problems/coupon-code-validator)

[English Version](/solution/3600-3699/3606.Coupon%20Code%20Validator/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你三个长度为 <code>n</code> 的数组，分别描述 <code>n</code> 个优惠券的属性：<code>code</code>、<code>businessLine</code> 和 <code>isActive</code>。其中，第 <code>i</code> 个优惠券具有以下属性：</p>

<ul>
	<li><code>code[i]</code>：一个 <strong>字符串</strong>，表示优惠券的标识符。</li>
	<li><code>businessLine[i]</code>：一个 <strong>字符串</strong>，表示优惠券所属的业务类别。</li>
	<li><code>isActive[i]</code>：一个 <strong>布尔值</strong>，表示优惠券是否当前有效。</li>
</ul>

<p>当以下所有条件都满足时，优惠券被认为是&nbsp;<strong>有效的&nbsp;</strong>：</p>

<ol>
	<li><code>code[i]</code> 不能为空，并且仅由字母数字字符（a-z、A-Z、0-9）和下划线（<code>_</code>）组成。</li>
	<li><code>businessLine[i]</code> 必须是以下四个类别之一：<code>"electronics"</code>、<code>"grocery"</code>、<code>"pharmacy"</code>、<code>"restaurant"</code>。</li>
	<li><code>isActive[i]</code> 为 <strong>true&nbsp;</strong>。</li>
</ol>

<p>返回所有&nbsp;<strong>有效优惠券的标识符&nbsp;</strong>组成的数组，按照以下规则排序：</p>

<ul>
	<li>先按照其 <strong>businessLine</strong> 的顺序排序：<code>"electronics"</code>、<code>"grocery"</code>、<code>"pharmacy"</code>、<code>"restaurant"</code>。</li>
	<li>在每个类别内，再按照 <strong>标识符的字典序（升序）</strong>排序。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">code = ["SAVE20","","PHARMA5","SAVE@20"], businessLine = ["restaurant","grocery","pharmacy","restaurant"], isActive = [true,true,true,true]</span></p>

<p><strong>输出：</strong> <span class="example-io">["PHARMA5","SAVE20"]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第一个优惠券有效。</li>
	<li>第二个优惠券的标识符为空（无效）。</li>
	<li>第三个优惠券有效。</li>
	<li>第四个优惠券的标识符包含特殊字符 <code>@</code>（无效）。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">code = ["GROCERY15","ELECTRONICS_50","DISCOUNT10"], businessLine = ["grocery","electronics","invalid"], isActive = [false,true,true]</span></p>

<p><strong>输出：</strong> <span class="example-io">["ELECTRONICS_50"]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第一个优惠券无效，因为它未激活。</li>
	<li>第二个优惠券有效。</li>
	<li>第三个优惠券无效，因为其业务类别无效。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == code.length == businessLine.length == isActive.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= code[i].length, businessLine[i].length &lt;= 100</code></li>
	<li><code>code[i]</code> 和 <code>businessLine[i]</code> 由可打印的 ASCII 字符组成。</li>
	<li><code>isActive[i]</code> 的值为 <code>true</code> 或 <code>false</code>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以直接模拟题目中的条件来筛选出有效的优惠券。具体步骤如下：

1. **检查标识符**：对于每个优惠券的标识符，检查它是否非空，并且只包含字母、数字和下划线。
2. **检查业务类别**：检查每个优惠券的业务类别是否属于给定的四个有效类别之一。
3. **检查激活状态**：检查每个优惠券是否处于激活状态。
4. **收集有效优惠券**：将所有满足上述条件的优惠券的 id 收集起来。
5. **排序**：根据业务类别和标识符对有效优惠券进行排序。
6. **返回结果**：返回排序后的有效优惠券的标识符列表。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$，其中 $n$ 是优惠券的数量。

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
