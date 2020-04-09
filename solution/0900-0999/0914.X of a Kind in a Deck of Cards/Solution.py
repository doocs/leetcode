# Algorithm
'''
1) Use a hashmap to store occurences
2) Find the min value in that hashmap, then find all of its factor
3) Return true when every occurences share that factor

'''
# Performance
'''
Runtime: 144 ms, faster than 87.35% of Python3 online submissions for X of a Kind in a Deck of Cards.
Memory Usage: 12.9 MB, less than 100.00% of Python3 online submissions for X of a Kind in a Deck of Cards.
'''

from collections import defaultdict
class Solution:
    def hasGroupsSizeX(self, deck: List[int]) -> bool:
        
        if len(deck) < 2:
            return False
        
        occurences = {}
        for card in deck:
            if card not in occurences:
                occurences[card] = 1
            else:
                occurences[card] += 1
        
        min_occurence = occurences[min(occurences.keys(), key = (lambda k: occurences[k]))]
    
        for factor in range(2, min_occurence+1):
            if min_occurence % factor == 0:
                check = True
                for value in occurences.values():
                    if value % factor != 0:
                        check = False
                        break
                if check:
                    return True
         
        return False
            
            
        