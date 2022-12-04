# [1797. Design Authentication Manager](https://leetcode.com/problems/design-authentication-manager)

[中文文档](/solution/1700-1799/1797.Design%20Authentication%20Manager/README.md)

## Description

<p>There is an authentication system that works with authentication tokens. For each session, the user will receive a new authentication token that will expire <code>timeToLive</code> seconds after the <code>currentTime</code>. If the token is renewed, the expiry time will be <b>extended</b> to expire <code>timeToLive</code> seconds after the (potentially different) <code>currentTime</code>.</p>

<p>Implement the <code>AuthenticationManager</code> class:</p>

<ul>
	<li><code>AuthenticationManager(int timeToLive)</code> constructs the <code>AuthenticationManager</code> and sets the <code>timeToLive</code>.</li>
	<li><code>generate(string tokenId, int currentTime)</code> generates a new token with the given <code>tokenId</code> at the given <code>currentTime</code> in seconds.</li>
	<li><code>renew(string tokenId, int currentTime)</code> renews the <strong>unexpired</strong> token with the given <code>tokenId</code> at the given <code>currentTime</code> in seconds. If there are no unexpired tokens with the given <code>tokenId</code>, the request is ignored, and nothing happens.</li>
	<li><code>countUnexpiredTokens(int currentTime)</code> returns the number of <strong>unexpired</strong> tokens at the given currentTime.</li>
</ul>

<p>Note that if a token expires at time <code>t</code>, and another action happens on time <code>t</code> (<code>renew</code> or <code>countUnexpiredTokens</code>), the expiration takes place <strong>before</strong> the other actions.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1797.Design%20Authentication%20Manager/images/copy-of-pc68_q2.png" style="width: 500px; height: 287px;" />
<pre>
<strong>Input</strong>
[&quot;AuthenticationManager&quot;, &quot;<code>renew</code>&quot;, &quot;generate&quot;, &quot;<code>countUnexpiredTokens</code>&quot;, &quot;generate&quot;, &quot;<code>renew</code>&quot;, &quot;<code>renew</code>&quot;, &quot;<code>countUnexpiredTokens</code>&quot;]
[[5], [&quot;aaa&quot;, 1], [&quot;aaa&quot;, 2], [6], [&quot;bbb&quot;, 7], [&quot;aaa&quot;, 8], [&quot;bbb&quot;, 10], [15]]
<strong>Output</strong>
[null, null, null, 1, null, null, null, 0]

<strong>Explanation</strong>
AuthenticationManager authenticationManager = new AuthenticationManager(5); // Constructs the AuthenticationManager with <code>timeToLive</code> = 5 seconds.
authenticationManager.<code>renew</code>(&quot;aaa&quot;, 1); // No token exists with tokenId &quot;aaa&quot; at time 1, so nothing happens.
authenticationManager.generate(&quot;aaa&quot;, 2); // Generates a new token with tokenId &quot;aaa&quot; at time 2.
authenticationManager.<code>countUnexpiredTokens</code>(6); // The token with tokenId &quot;aaa&quot; is the only unexpired one at time 6, so return 1.
authenticationManager.generate(&quot;bbb&quot;, 7); // Generates a new token with tokenId &quot;bbb&quot; at time 7.
authenticationManager.<code>renew</code>(&quot;aaa&quot;, 8); // The token with tokenId &quot;aaa&quot; expired at time 7, and 8 &gt;= 7, so at time 8 the <code>renew</code> request is ignored, and nothing happens.
authenticationManager.<code>renew</code>(&quot;bbb&quot;, 10); // The token with tokenId &quot;bbb&quot; is unexpired at time 10, so the <code>renew</code> request is fulfilled and now the token will expire at time 15.
authenticationManager.<code>countUnexpiredTokens</code>(15); // The token with tokenId &quot;bbb&quot; expires at time 15, and the token with tokenId &quot;aaa&quot; expired at time 7, so currently no token is unexpired, so return 0.

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= timeToLive &lt;= 10<sup>8</sup></code></li>
	<li><code>1 &lt;= currentTime &lt;= 10<sup>8</sup></code></li>
	<li><code>1 &lt;= tokenId.length &lt;= 5</code></li>
	<li><code>tokenId</code> consists only of lowercase letters.</li>
	<li>All calls to <code>generate</code> will contain unique values of <code>tokenId</code>.</li>
	<li>The values of <code>currentTime</code> across all the function calls will be <strong>strictly increasing</strong>.</li>
	<li>At most <code>2000</code> calls will be made to all functions combined.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class AuthenticationManager:

    def __init__(self, timeToLive: int):
        self.t = timeToLive
        self.d = defaultdict(int)

    def generate(self, tokenId: str, currentTime: int) -> None:
        self.d[tokenId] = currentTime + self.t

    def renew(self, tokenId: str, currentTime: int) -> None:
        if self.d[tokenId] <= currentTime:
            return
        self.d[tokenId] = currentTime + self.t

    def countUnexpiredTokens(self, currentTime: int) -> int:
        return sum(exp > currentTime for exp in self.d.values())


# Your AuthenticationManager object will be instantiated and called as such:
# obj = AuthenticationManager(timeToLive)
# obj.generate(tokenId,currentTime)
# obj.renew(tokenId,currentTime)
# param_3 = obj.countUnexpiredTokens(currentTime)
```

### **Java**

```java
class AuthenticationManager {
    private int t;
    private Map<String, Integer> d = new HashMap<>();

    public AuthenticationManager(int timeToLive) {
        t = timeToLive;
    }

    public void generate(String tokenId, int currentTime) {
        d.put(tokenId, currentTime + t);
    }

    public void renew(String tokenId, int currentTime) {
        if (d.getOrDefault(tokenId, 0) <= currentTime) {
            return;
        }
        generate(tokenId, currentTime);
    }

    public int countUnexpiredTokens(int currentTime) {
        int ans = 0;
        for (int exp : d.values()) {
            if (exp > currentTime) {
                ++ans;
            }
        }
        return ans;
    }
}

/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * AuthenticationManager obj = new AuthenticationManager(timeToLive);
 * obj.generate(tokenId,currentTime);
 * obj.renew(tokenId,currentTime);
 * int param_3 = obj.countUnexpiredTokens(currentTime);
 */
```

### **C++**

```cpp
class AuthenticationManager {
public:
    AuthenticationManager(int timeToLive) {
        t = timeToLive;
    }

    void generate(string tokenId, int currentTime) {
        d[tokenId] = currentTime + t;
    }

    void renew(string tokenId, int currentTime) {
        if (d[tokenId] <= currentTime) return;
        generate(tokenId, currentTime);
    }

    int countUnexpiredTokens(int currentTime) {
        int ans = 0;
        for (auto& [_, v] : d) ans += v > currentTime;
        return ans;
    }

private:
    int t;
    unordered_map<string, int> d;
};

/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * AuthenticationManager* obj = new AuthenticationManager(timeToLive);
 * obj->generate(tokenId,currentTime);
 * obj->renew(tokenId,currentTime);
 * int param_3 = obj->countUnexpiredTokens(currentTime);
 */
```

### **Go**

```go
type AuthenticationManager struct {
	t int
	d map[string]int
}

func Constructor(timeToLive int) AuthenticationManager {
	return AuthenticationManager{timeToLive, map[string]int{}}
}

func (this *AuthenticationManager) Generate(tokenId string, currentTime int) {
	this.d[tokenId] = currentTime + this.t
}

func (this *AuthenticationManager) Renew(tokenId string, currentTime int) {
	if v, ok := this.d[tokenId]; !ok || v <= currentTime {
		return
	}
	this.Generate(tokenId, currentTime)
}

func (this *AuthenticationManager) CountUnexpiredTokens(currentTime int) int {
	ans := 0
	for _, exp := range this.d {
		if exp > currentTime {
			ans++
		}
	}
	return ans
}

/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * obj := Constructor(timeToLive);
 * obj.Generate(tokenId,currentTime);
 * obj.Renew(tokenId,currentTime);
 * param_3 := obj.CountUnexpiredTokens(currentTime);
 */
```

### **TypeScript**

```ts
class AuthenticationManager {
    private timeToLive: number;
    private map: Map<string, number>;

    constructor(timeToLive: number) {
        this.timeToLive = timeToLive;
        this.map = new Map<string, number>();
    }

    generate(tokenId: string, currentTime: number): void {
        this.map.set(tokenId, currentTime + this.timeToLive);
    }

    renew(tokenId: string, currentTime: number): void {
        if ((this.map.get(tokenId) ?? 0) <= currentTime) {
            return;
        }
        this.map.set(tokenId, currentTime + this.timeToLive);
    }

    countUnexpiredTokens(currentTime: number): number {
        let res = 0;
        for (const time of this.map.values()) {
            if (time > currentTime) {
                res++;
            }
        }
        return res;
    }
}

/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * var obj = new AuthenticationManager(timeToLive)
 * obj.generate(tokenId,currentTime)
 * obj.renew(tokenId,currentTime)
 * var param_3 = obj.countUnexpiredTokens(currentTime)
 */
```

### **Rust**

```rust
use std::collections::HashMap;
struct AuthenticationManager {
    time_to_live: i32,
    map: HashMap<String, i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl AuthenticationManager {
    fn new(timeToLive: i32) -> Self {
        Self {
            time_to_live: timeToLive,
            map: HashMap::new(),
        }
    }

    fn generate(&mut self, token_id: String, current_time: i32) {
        self.map.insert(token_id, current_time + self.time_to_live);
    }

    fn renew(&mut self, token_id: String, current_time: i32) {
        if self.map.get(&token_id).unwrap_or(&0) <= &current_time {
            return;
        }
        self.map.insert(token_id, current_time + self.time_to_live);
    }

    fn count_unexpired_tokens(&self, current_time: i32) -> i32 {
        self.map.values().filter(|&time| *time > current_time).count() as i32
    }
}


/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * let obj = AuthenticationManager::new(timeToLive);
 * obj.generate(tokenId, currentTime);
 * obj.renew(tokenId, currentTime);
 * let ret_3: i32 = obj.count_unexpired_tokens(currentTime);
 */
```

### **...**

```

```

<!-- tabs:end -->
