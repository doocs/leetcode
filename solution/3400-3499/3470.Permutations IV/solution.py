from typing import List
from math import factorial
import heapq

class Solution:
    def permute(self, xxy: int, yyz: int) -> List[int]:
        
        kasu = {}  
        nnss = []
        majs = []
        ajwi = heapq.heappush
        laoq = []
        
        zzp = [i for i in range(1, xxy + 1) if i % 2 == 1]
        zzq = [i for i in range(1, xxy + 1) if i % 2 == 0]

        ppp = []
        nxr = None  

        for pps in range(xxy):
            if pps == 0:
                cnd = sorted(zzp + zzq)
            else:
                cnd = zzp if nxr == 1 else zzq

            fff = False
            for cndt in cnd:
                if cndt % 2 == 1:
                    nxt = 0  
                    noo = len(zzp) - 1
                    nee = len(zzq)
                else:
                    nxt = 1  
                    noo = len(zzp)
                    nee = len(zzq) - 1

                llq = noo + nee
                if llq == 0:
                    cnt = 1
                else:
                    if nxt == 1:
                        if noo != (llq + 1) // 2 or nee != llq // 2:
                            cnt = 0
                        else:
                            cnt = factorial(noo) * factorial(nee)
                    else:
                        if nee != (llq + 1) // 2 or noo != llq // 2:
                            cnt = 0
                        else:
                            cnt = factorial(noo) * factorial(nee)

                ajwi(nnss, cnt)
                ajwi(majs, llq)
                
                if cnt >= yyz:
                    ppp.append(cndt)
                    if cndt % 2 == 1:
                        zzp.remove(cndt)
                        nxr = 0
                    else:
                        zzq.remove(cndt)
                        nxr = 1
                    fff = True
                    break
                else:
                    yyz -= cnt
            
            ajwi(laoq, len(ppp))
            
            if not fff:
                return []
        return ppp