---
comments: true
difficulty: Easy
tags:
    - Design
    - Array
    - Hash Table
    - Two Pointers
    - Data Stream
---

<!-- problem:start -->

# [170. Two Sum III - Data structure design 🔒](https://leetcode.com/problems/two-sum-iii-data-structure-design)

[中文文档](/solution/0100-0199/0170.Two%20Sum%20III%20-%20Data%20structure%20design/README.md)

## Mô tả

<!-- description:start -->

<p>Thiết kế một cấu trúc dữ liệu chấp nhận một luồng số nguyên và kiểm tra xem luồng đó có một cặp số nguyên có tổng bằng một giá trị cụ thể hay không.</p>

<p>Triển khai lớp <code>TwoSum</code>:</p>

<ul>
	<li><code>TwoSum()</code> Khởi tạo đối tượng <code>TwoSum</code>, ban đầu là một mảng rỗng.</li>
	<li><code>void add(int number)</code> Thêm <code>number</code> vào cấu trúc dữ liệu.</li>
	<li><code>boolean find(int value)</code> Trả về <code>true</code> nếu tồn tại bất kỳ cặp số nào có tổng bằng <code>value</code>, nếu không thì trả về <code>false</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào</strong>
[&quot;TwoSum&quot;, &quot;add&quot;, &quot;add&quot;, &quot;add&quot;, &quot;find&quot;, &quot;find&quot;]
[[], [1], [3], [5], [4], [7]]
<strong>Đầu ra</strong>
[null, null, null, null, true, false]

<strong>Giải thích</strong>
TwoSum twoSum = new TwoSum();
twoSum.add(1);   // [] --&gt; [1]
twoSum.add(3);   // [1] --&gt; [1,3]
twoSum.add(5);   // [1,3] --&gt; [1,3,5]
twoSum.find(4);  // 1 + 3 = 4, return true
twoSum.find(7);  // No two integers sum up to 7, return false
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>-10<sup>5</sup> &lt;= number &lt;= 10<sup>5</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= value &lt;= 2<sup>31</sup> - 1</code></li>
	<li>Tối đa <code>10<sup>4</sup></code> lần gọi sẽ được thực hiện cho <code>add</code> và <code>find</code>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Bảng băm

<!-- thinking:start -->

> **Tư duy**
>
> Hỗ trợ thêm các số và truy vấn xem hai số có tổng bằng một giá trị hay không. Có tối đa $10^4$ lần gọi. Việc sắp xếp ở mỗi $\textit{find}$ bị vô hiệu bởi các lần thêm sau đó. Một bảng tần suất giúp $\textit{add}$ có độ phức tạp $O(1)$; $\textit{find}$ thử từng $x$ và tra cứu $value-x$, yêu cầu số lượng ít nhất là $2$ khi $x$ bằng $value-x$.

<!-- thinking:end -->

Chúng ta sử dụng một bảng băm `cnt` để lưu số lần xuất hiện của mỗi số.

Khi gọi phương thức `add`, chúng ta tăng số lần xuất hiện của số `number`.

Khi gọi phương thức `find`, chúng ta duyệt qua bảng băm `cnt`. Với mỗi khóa `x`, chúng ta kiểm tra xem `value - x` có phải cũng là một khóa trong bảng băm `cnt` hay không. Nếu có, chúng ta kiểm tra xem `x` có bằng `value - x` hay không. Nếu chúng không bằng nhau, điều đó có nghĩa là chúng ta đã tìm thấy một cặp số có tổng bằng `value`, và trả về `true`. Nếu chúng bằng nhau, chúng ta kiểm tra xem số lần xuất hiện của `x` có lớn hơn `1` hay không. Nếu có, điều đó có nghĩa là chúng ta đã tìm thấy một cặp số có tổng bằng `value`, và trả về `true`. Nếu nhỏ hơn hoặc bằng `1`, điều đó có nghĩa là chúng ta chưa tìm thấy một cặp số có tổng bằng `value`, và tiếp tục duyệt qua bảng băm `cnt`. Nếu không tìm thấy một cặp số nào sau khi duyệt, chúng ta trả về `false`.

Độ phức tạp thời gian:

- Độ phức tạp thời gian của phương thức `add` là $O(1)$.
- Độ phức tạp thời gian của phương thức `find` là $O(n)$.

Độ phức tạp không gian là $O(n)$, trong đó $n$ là kích thước của bảng băm `cnt`.

<!-- tabs:start -->

#### Python3

```python
class TwoSum:

    def __init__(self):
        self.cnt = defaultdict(int)

    def add(self, number: int) -> None:
        self.cnt[number] += 1

    def find(self, value: int) -> bool:
        for x, v in self.cnt.items():
            y = value - x
            if y in self.cnt and (x != y or v > 1):
                return True
        return False


# Đối tượng TwoSum của bạn sẽ được khởi tạo và gọi như sau:
# obj = TwoSum()
# obj.add(number)
# param_2 = obj.find(value)
```

#### Java

```java
class TwoSum {
    private Map<Integer, Integer> cnt = new HashMap<>();

    public TwoSum() {
    }

    public void add(int number) {
        cnt.merge(number, 1, Integer::sum);
    }

    public boolean find(int value) {
        for (var e : cnt.entrySet()) {
            int x = e.getKey(), v = e.getValue();
            int y = value - x;
            if (cnt.containsKey(y) && (x != y || v > 1)) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Đối tượng TwoSum của bạn sẽ được khởi tạo và gọi như sau:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
```

#### C++

```cpp
class TwoSum {
public:
    TwoSum() {
    }

    void add(int number) {
        ++cnt[number];
    }

    bool find(int value) {
        for (auto& [x, v] : cnt) {
            long y = (long) value - x;
            if (cnt.contains(y) && (x != y || v > 1)) {
                return true;
            }
        }
        return false;
    }

private:
    unordered_map<int, int> cnt;
};

/**
 * Đối tượng TwoSum của bạn sẽ được khởi tạo và gọi như sau:
 * TwoSum* obj = new TwoSum();
 * obj->add(number);
 * bool param_2 = obj->find(value);
 */
```

#### Go

```go
type TwoSum struct {
	cnt map[int]int
}

func Constructor() TwoSum {
	return TwoSum{map[int]int{}}
}

func (this *TwoSum) Add(number int) {
	this.cnt[number] += 1
}

func (this *TwoSum) Find(value int) bool {
	for x, v := range this.cnt {
		y := value - x
		if _, ok := this.cnt[y]; ok && (x != y || v > 1) {
			return true
		}
	}
	return false
}

/**
 * Đối tượng TwoSum của bạn sẽ được khởi tạo và gọi như sau:
 * obj := Constructor();
 * obj.Add(number);
 * param_2 := obj.Find(value);
 */
```

#### TypeScript

```ts
class TwoSum {
    private cnt: Map<number, number> = new Map();
    constructor() {}

    add(number: number): void {
        this.cnt.set(number, (this.cnt.get(number) || 0) + 1);
    }

    find(value: number): boolean {
        for (const [x, v] of this.cnt) {
            const y = value - x;
            if (this.cnt.has(y) && (x !== y || v > 1)) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Đối tượng TwoSum của bạn sẽ được khởi tạo và gọi như sau:
 * var obj = new TwoSum()
 * obj.add(number)
 * var param_2 = obj.find(value)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
