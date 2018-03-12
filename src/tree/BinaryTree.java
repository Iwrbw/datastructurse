package tree;

/**
 * Create by yangshunfan
 * 2018/3/10 22:54
 * 二叉树实现
 */
public class BinaryTree {
    Object element; //自身元素
    BinaryTree left; //左儿子
    BinaryTree right; //右儿子

    /**
     * 二叉查找树的实现
     */
    private static class BinarySearchTree<AnyType> {
        /**
         * 内部类（树）
         * @param <AnyType>
         */
        private static class BinaryNode<AnyType> {
            Object element;
            BinaryNode left; //左儿子
            BinaryNode right; //右儿子

            /**
             * 构造方法
             * @param element
             * @param left
             * @param right
             */
            BinaryNode(Object element, BinaryNode left, BinaryNode right) {
                this.element = element;
                this.left = left;
                this.right = right;
            }

            BinaryNode(AnyType element) {
                this(element,null,null);
            }
        }

        private BinaryNode<AnyType> root;


        public BinarySearchTree() {
            root = null;
        }

        /**
         * 是否为空树
         * @return
         */
        public boolean isEmpty() {
            return root == null;
        }

        /**
         * 使树为空
         */
        public void makeEmpty() {
            root = null;
        }

        /**
         * 是否包含o节点
         * @param o
         * @return
         */
        public boolean contains(String o) { //这里假设是用String类型
            return contains(o,root);
        }

        public boolean contains(String o, BinaryNode node) {
            if (node == null) {
                return false;
            }
            //这里假设实现了compareTo方法
            int compareResult = o.compareTo(String.valueOf(node));
            if (compareResult == 0) {
                return true;
            } else if (compareResult < 0) {
                return contains(o,node.left);
            } else {
                return contains(o,node.right);
            }
        }

        /**
         * 查找最大的元素
         * @return
         */
        public BinaryNode findMax() {
            return findMax(root);
        }

        public BinaryNode findMax(BinaryNode node) {
            if (node != null) {
                while (node.right != null) {
                    node = node.right;
                }
            }
            return node;
        }

        /**
         * 查找最小的元素
         */
        public BinaryNode findMin() {
            return findMin(root);
        }

        public BinaryNode findMin(BinaryNode node) {
            if (node != null) {
                while (node.left != null) {
                    node = node.left;
                }
            }
            return node;
        }

        /**
         * insert()插入方法,这里用String类型代替
         */
        public void insert(String obj) {
            root = insert(obj,root);
        }

        public BinaryNode insert(String obj,BinaryNode node) {
            if (node == null) {
                return new BinaryNode(obj,null,null);
            }

            int compareResutl = obj.compareTo(String.valueOf(node));

            if (compareResutl < 0) {
                node.left = insert(obj,node.left);
            } else if (compareResutl > 0) {
                node.right = insert(obj,node.right);
            }
            return node;
        }

        /**
         * remove()移除方法
         * 这个方法最难实现
         */
        public BinaryNode remove(String obj) {
            return remove(obj,root);
        }
        public BinaryNode remove(String obj,BinaryNode node) {
            if (node == null) {
                return node;
            }

            int compareResult = obj.compareTo(String.valueOf(node));

            /**
             * 前两个if用来找到节点的
             */
            if (compareResult < 0) {
                node.left = remove(obj,node.left);
            } else if (compareResult > 0) {
                node.right = remove(obj,node.right);
            } else if (node.left != null && node.right != null) { //如果有两个儿子
                //找到移除节点的右字数最小的节点，代替移除的位置
                node.element = findMin(node.right).element;
                node.right = remove((String) node.element,node.right);
            } else {
                node = (node.left != null) ? node.left : node.right;
            }
            return node;

        }
    }
}
