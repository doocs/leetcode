from collections import Counter, deque

class Solution:
    def longestSubsequenceRepeatedK(self, s: str, k: int) -> str:
        # Step 1: Frequency counter for each character in the string
        freq = Counter(s)
        
        # Step 2: Collect all characters with at least k frequency
        candidates = [ch for ch, count in freq.items() if count >= k]
        
        # Step 3: Helper function to check if a given sequence can be repeated k times
        def canBeRepeatedKTimes(subseq):
            i = 0
            count = 0
            for ch in s:
                if ch == subseq[i]:
                    i += 1
                    if i == len(subseq):
                        i = 0
                        count += 1
                        if count == k:
                            return True
            return False

        # Step 4: Breadth-First Search (BFS) to generate subsequences
        queue = deque([""])  # Start with an empty sequence
        longest = ""

        while queue:
            curr = queue.popleft()

            # Try appending each candidate character to the current subsequence
            for ch in candidates:
                new_subseq = curr + ch
                
                # Check if the new subsequence is repeatable k times
                if canBeRepeatedKTimes(new_subseq):
                    queue.append(new_subseq)
                    
                    # Update longest if new_subseq is longer or lexicographically larger
                    if len(new_subseq) > len(longest) or (len(new_subseq) == len(longest) and new_subseq > longest):
                        longest = new_subseq

        return longest


# Example usage:
sol = Solution()
print(sol.longestSubsequenceRepeatedK("letsleetcode", 2))  # Output: "let"
print(sol.longestSubsequenceRepeatedK("bb", 2))  # Output: "b"
print(sol.longestSubsequenceRepeatedK("ab", 2))  # Output: ""
print(sol.longestSubsequenceRepeatedK("bbabbabbbbabaababab", 3))  # Output: "bbbb"
