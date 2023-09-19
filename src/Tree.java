public class Tree {

    private Integer root;
    private List<Tree> subtrees;

    Tree root;
    Tree subtrees;

    private static String str(){
        return Tree.str_indented();
    }

    private static String str_indented(Integer depth){
        if (Tree.is_empty()){
            return "";
        }
        else{
            StringBuilder s = new StringBuilder((" " * depth) + str(Tree.root) + '\n');
            for (Tree.root: subtree){
                s.append(subtree.str_indented(depth + 1));
            }
            return s.toString();
        }
    }

    private static String str_indented(){
        return "";
    }

    private void delete_root() {
    /*Remove the root item of this tree.

    Precondition: this tree has at least one subtree.
    */
        if (subtrees.isEmpty()) {
            // This is a leaf. Deleting the root gives and empty tree.
            root = null;
        }
        else {
            // Get the last subtree in this tree.
            Tree chosen_subtree = subtrees.pop();
            self.root = chosen_subtree.root;
            subtrees.extend(chosen_subtree.subtrees);

        /*Strategy 2: Replace with a leaf.
        1. Extract the leftmost leaf (using another helper).
        leaf = self._extract_leaf()

        2. Update self._root. (Note that self._subtrees remains the same.)
        self._root = leaf*/
        }
    }

    private Optional extract_leaf() {
    /*Remove and return the leftmost leaf in a tree.

    Precondition: this tree is non-empty.
    */
        if (subtrees.isEmpty()){
            Optional old_root = root;
            root = null;
            return old_root;
        }
        else {
            Optional leaf = subtrees.get(0).extract_leaf();
        /*Need to check whether self._subtrees[0] is now empty,
        and if so, remove it.*/
            if (subtrees.isEmpty()) {
                subtrees.pop(0);
            }
            return leaf;
        }
    }

    public Optional insert(Optional item) {
    /*Insert <item> into this tree using the following algorithm.

        1. If the tree is empty, <item> is the new root of the tree.
        2. If the tree has a root but no subtrees, create a
           new tree containing the item, and make this new tree a subtree
           of the original tree.
        3. Otherwise, pick a random number between 1 and 3 inclusive.
            - If the random number is 3, create a new tree containing
              the item, and make this new tree a subtree of the original.
            - If the random number is a 1 or 2, pick one of the existing
              subtrees at random, and *recursively insert* the new item
              into that subtree.

    >>> t = Tree(None, [])
    >>> t.insert(1)
    >>> 1 in t
    True
    >>> lt = Tree(2, [Tree(4, []), Tree(5, [])])
    >>> rt = Tree(3, [Tree(6, []), Tree(7, []), Tree(8, []), Tree(9, []),\
                      Tree(10, [])])
    >>> t = Tree(1, [lt, rt])
    >>> t.insert(100)
    >>> 100 in t
    True
    */
        if (root == null) {
            root = item;
        }
        else if (subtrees.isEmpty()) {
            subtrees = new ArrayList<Tree>(Tree(item, []));
        }
        else {
            if (rand.nextInt(1, 3) == 3){
                subtrees.add(Tree(item, []));
            }
            else {
                int subtree_index = rand.nextInt(0, subtrees.size() - 1);
                subtrees.get(subtree_index).insert(item);
            }
        }
    }

    public boolean insert_child(Optional item, Optional parent){
    /*Insert <item> into this tree as a child of <parent>.

    If successful, return True. If <parent> is not in this tree,
    return False.

    If <parent> appears more than once in this tree, <item> should only
    be inserted once (you can pick where to insert it).
    */
        if (root == null) {
            return false;
        }
        else if (root == parent) {
            subtrees.add(Tree(item, []));
            return true;
        }
        else{
            for (Tree sub : subtrees) {
                if (sub.insert_child(item, parent)) {
                    return true;
                }
            }
            return false;
        }
    }
    }

}

