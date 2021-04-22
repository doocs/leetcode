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
<p><strong>Example 1:</strong></p>
<img alt="" src="/solution/1700-1799/1797.Design Authentication Manager/images/copy-of-pc68_q2.png" style="width: 500px; height: 287px;" />
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

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
