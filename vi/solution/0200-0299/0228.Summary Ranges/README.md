---
comments: true
difficulty: Easy
tags:
    - Array
---

<!-- problem:start -->

# [228. Summary Ranges](https://leetcode.com/problems/summary-ranges)

[中文文档](/solution/0200-0299/0228.Summary%20Ranges/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cho một mảng số nguyên <code>nums</code> <strong>đã sắp xếp và không trùng lặp</strong>.</p>

<p>Một <strong>khoảng</strong> <code>[a,b]</code> là tập hợp tất cả các số nguyên từ <code>a</code> đến <code>b</code> (bao gồm cả hai đầu mút).</p>

<p>Hãy trả về <em>danh sách các khoảng <strong>nhỏ nhất, đã sắp xếp</strong> bao phủ <strong>chính xác tất cả các số trong mảng</strong></em>. Nghĩa là, mỗi phần tử của <code>nums</code> được bao phủ bởi đúng một khoảng, và không tồn tại số nguyên <code>x</code> nào sao cho <code>x</code> nằm trong một trong các khoảng nhưng không nằm trong <code>nums</code>.</p>

<p>Mỗi khoảng <code>[a,b]</code> trong danh sách phải được xuất ra như sau:</p>

<ul>
	<li><code>&quot;a-&gt;b&quot;</code> nếu <code>a != b</code></li>
	<li><code>&quot;a&quot;</code> nếu <code>a == b</code></li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [0,1,2,4,5,7]
<strong>Đầu ra:</strong> [&quot;0-&gt;2&quot;,&quot;4-&gt;5&quot;,&quot;7&quot;]
<strong>Giải thích:</strong> Các khoảng là:
[0,2] --&gt; &quot;0-&gt;2&quot;
[4,5] --&gt; &quot;4-&gt;5&quot;
[7,7] --&gt; &quot;7&quot;
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [0,2,3,4,6,8,9]
<strong>Đầu ra:</strong> [&quot;0&quot;,&quot;2-&gt;4&quot;,&quot;6&quot;,&quot;8-&gt;9&quot;]
<strong>Giải thích:</strong> Các khoảng là:
[0,0] --&gt; &quot;0&quot;
[2,4] --&gt; &quot;2-&gt;4&quot;
[6,6] --&gt; &quot;6&quot;
[8,9] --&gt; &quot;8-&gt;9&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 20</code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li>Tất cả các giá trị của <code>nums</code> đều <strong>không trùng lặp</strong>.</li>
	<li><code>nums</code> được sắp xếp theo thứ tự tăng dần.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Hai con trỏ

<!-- thinking:start -->

> **Tư duy**
>
> Mảng đã được sắp xếp và không trùng lặp, nên một khoảng là một dãy liên tiếp cực đại gồm các phần tử kề nhau chênh lệch $1$. Chỉ cần duyệt một lần để chia các dãy này.
>
> Hai con trỏ $i,j$ đánh dấu một đoạn cho đến khi $nums[j+1]\neq nums[j]+1$, sau đó định dạng đoạn đơn phần tử hoặc $a{\to}b$.

<!-- thinking:end -->

Chúng ta có thể sử dụng hai con trỏ $i$ và $j$ để tìm điểm đầu và điểm cuối của mỗi khoảng.

Duyệt qua mảng, khi $j + 1 < n$ và $nums[j + 1] = nums[j] + 1$, di chuyển $j$ sang phải; nếu không, khoảng $[i, j]$ đã được tìm thấy, thêm khoảng đó vào đáp án, sau đó di chuyển $i$ đến vị trí của $j + 1$ và tiếp tục tìm khoảng tiếp theo.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của mảng. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def summaryRanges(self, nums: List[int]) -> List[str]:
        def f(i: int, j: int) -> str:
            return str(nums[i]) if i == j else f'{nums[i]}->{nums[j]}'

        i = 0
        n = len(nums)
        ans = []
        while i < n:
            j = i
            while j + 1 < n and nums[j + 1] == nums[j] + 1:
                j += 1
            ans.append(f(i, j))
            i = j + 1
        return ans
```

#### Java

```java
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        for (int i = 0, j, n = nums.length; i < n; i = j + 1) {
            j = i;
            while (j + 1 < n && nums[j + 1] == nums[j] + 1) {
                ++j;
            }
            ans.add(f(nums, i, j));
        }
        return ans;
    }

    private String f(int[] nums, int i, int j) {
        return i == j ? nums[i] + "" : String.format("%d->%d", nums[i], nums[j]);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> summaryRanges(vector<int>& nums) {
        vector<string> ans;
        auto f = [&](int i, int j) {
            return i == j ? to_string(nums[i]) : to_string(nums[i]) + "->" + to_string(nums[j]);
        };
        for (int i = 0, j, n = nums.size(); i < n; i = j + 1) {
            j = i;
            while (j + 1 < n && nums[j + 1] == nums[j] + 1) {
                ++j;
            }
            ans.emplace_back(f(i, j));
        }
        return ans;
    }
};
```

#### Go

```go
func summaryRanges(nums []int) (ans []string) {
	f := func(i, j int) string {
		if i == j {
			return strconv.Itoa(nums[i])
		}
		return strconv.Itoa(nums[i]) + "->" + strconv.Itoa(nums[j])
	}
	for i, j, n := 0, 0, len(nums); i < n; i = j + 1 {
		j = i
		for j+1 < n && nums[j+1] == nums[j]+1 {
			j++
		}
		ans = append(ans, f(i, j))
	}
	return
}
```

#### TypeScript

```ts
function summaryRanges(nums: number[]): string[] {
    const f = (i: number, j: number): string => {
        return i === j ? `${nums[i]}` : `${nums[i]}->${nums[j]}`;
    };
    const n = nums.length;
    const ans: string[] = [];
    for (let i = 0, j = 0; i < n; i = j + 1) {
        j = i;
        while (j + 1 < n && nums[j + 1] === nums[j] + 1) {
            ++j;
        }
        ans.push(f(i, j));
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn summary_ranges(nums: Vec<i32>) -> Vec<String> {
        if nums.is_empty() {
            return vec![];
        }

        let mut ret = Vec::new();
        let mut start = nums[0];
        let mut prev = nums[0];
        let mut current = 0;
        let n = nums.len();

        for i in 1..n {
            current = nums[i];
            if current != prev + 1 {
                if start == prev {
                    ret.push(start.to_string());
                } else {
                    ret.push(start.to_string() + "->" + &prev.to_string());
                }
                start = current;
                prev = current;
            } else {
                prev = current;
            }
        }

        if start == prev {
            ret.push(start.to_string());
        } else {
            ret.push(start.to_string() + "->" + &prev.to_string());
        }

        ret
    }
}
```

#### C#

```cs
public class Solution {
    public IList<string> SummaryRanges(int[] nums) {
        var ans = new List<string>();
        for (int i = 0, j = 0, n = nums.Length; i < n; i = j + 1) {
            j = i;
            while (j + 1 < n && nums[j + 1] == nums[j] + 1) {
                ++j;
            }
            ans.Add(f(nums, i, j));
        }
        return ans;
    }

    public string f(int[] nums, int i, int j) {
        return i == j ? nums[i].ToString() : string.Format("{0}->{1}", nums[i], nums[j]);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
