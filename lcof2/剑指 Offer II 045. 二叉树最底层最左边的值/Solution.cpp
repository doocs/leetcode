class Solution
{
public:
    int findBottomLeftValue ( TreeNode* root )
    {
        if ( !root )
        {
            return 0;
        }

        int res = root->val;
        deque<TreeNode* > deq;
        deq.push_back ( root );

        while ( !deq.empty() )
        {
            int size = deq.size();

            for ( int i = 0; i < size; i++ )
            {
                TreeNode* ptr = deq.front();
                deq.pop_front();

                if ( i == 0 )
                {
                    res = ptr->val;
                }

                if ( ptr->left )
                {
                    deq.push_back ( ptr->left );
                }

                if ( ptr->right )
                {
                    deq.push_back ( ptr->right );
                }
            }
        }

        return res;
    }
};
