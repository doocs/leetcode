class Solution:
    def countOfPairs(self, n: int, x: int, y: int) -> List[int]:
        adj = collections.defaultdict( list )
        for i in range( 1 , n ):
            adj[i-1].append( i )
            adj[i].append( i-1 )
        if x != y :
            x-=1 
            y-=1 
            adj[x].append( y )
            adj[y].append( x )
        ans = [ 0 ]*n
        def bfs( start ):
            arr = [ inf ]*n
            q =  [ ( 0 , start ) ] 
            heapq.heapify( q )
            while q : 
                size = len( q )
                while size :
                    size -=1 
                    dist , node = heapq.heappop( q )
                    if arr[ node ] <= dist : continue 
                    arr[node] = dist 
                    for child in adj[ node ] : 
                        heapq.heappush( q , ( dist+1 , child ))
            for i in arr :
                if i != 0 and i!= inf :
                    ans[i-1] +=1

        for i in range(  n ):
            bfs( i )
        
        return ans

        
