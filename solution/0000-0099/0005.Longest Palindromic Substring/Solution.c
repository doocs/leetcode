

char *longestPalindrome(char *s) {
  int n = strlen(s);
  if (n == 0) {
    char *result = malloc(1);
    result[0] = '\0';
    return result;
  }

  bool dp[n][n];
  memset(dp, 0, sizeof(dp));

  int start = 0, max_len = 1;

  for (int i = 0; i < n; i++) {
    dp[i][i] = true;
  }

  for (int i = 0; i < n - 1; i++) {
    if (s[i] == s[i + 1]) {
      dp[i][i + 1] = true;
      start = i;
      max_len = 2;
    }
  }

  // Check for lengths > 2
  for (int len = 3; len <= n; len++) {
    for (int i = 0; i < n - len + 1; i++) {
      int j = i + len - 1;
      if (s[i] == s[j] && dp[i + 1][j - 1]) {
        dp[i][j] = true;
        if (len > max_len) {
          start = i;
          max_len = len;
        }
      }
    }
  }
  char *result = malloc(max_len + 1);
  strncpy(result, s + start, max_len);
  result[max_len] = '\0';
  return result;
}
