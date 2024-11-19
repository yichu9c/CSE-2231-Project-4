import java.util.Iterator;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.set.Set;
import components.set.SetSecondary;

/**
 * {@code Set} represented as a {@code BinaryTree} (maintained as a binary
 * search tree) of elements with implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Set} elements
 * @mathdefinitions <pre>
 * IS_BST(
 *   tree: binary tree of T
 *  ): boolean satisfies
 *  [tree satisfies the binary search tree properties as described in the
 *   slides with the ordering reported by compareTo for T, including that
 *   it has no duplicate labels]
 * </pre>
 * @convention IS_BST($this.tree)
 * @correspondence this = labels($this.tree)
 *
 * @author Shyam Sai Bethina and Yihone Chu
 *
 */
public class Set3a<T extends Comparable<T>> extends SetSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Elements included in {@code this}.
     */
    private BinaryTree<T> tree;

    /**
     * Returns whether {@code x} is in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be searched for
     * @return true if t contains x, false otherwise
     * @requires IS_BST(t)
     * @ensures isInTree = (x is in labels(t))
     */
    private static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        /*
         * Initializes answer value.
         */
        boolean answer = false;

        /*
         * Checks if t is not empty
         */
        if (t.size() > 0) {
            /*
             * If t is not empty, then disassembles the tree and sets it to the
             * correct trees and root variables.
             */
            BinaryTree<T> lhs = t.newInstance();
            BinaryTree<T> rhs = t.newInstance();

            T root = t.disassemble(lhs, rhs);

            /*
             * If the root equals x, then x is in the tree and answer is true.
             * Otherwise, if x is greater than the root, the statement
             * recursively checks if x is in the right tree, or if x is less
             * than the root, the statement recursively checks if x is in the
             * left tree.
             */
            if (x.equals(root)) {
                answer = true;
            } else if (x.compareTo(root) > 0) {
                answer = isInTree(rhs, x);
            } else {
                answer = isInTree(lhs, x);
            }

            /*
             * Reassembles the original tree to preserve it.
             */
            t.assemble(root, lhs, rhs);
        }
        return answer;
    }

    /**
     * Inserts {@code x} in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be inserted
     * @aliases reference {@code x}
     * @updates t
     * @requires IS_BST(t) and x is not in labels(t)
     * @ensures IS_BST(t) and labels(t) = labels(#t) union {x}
     */
    private static <T extends Comparable<T>> void insertInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        /*
         * If the tree is not empty, then it inserts x in the correct spot. If
         * the tree is empty, then a new tree is constructed with x being the
         * root and empty left and right trees.
         */
        if (t.size() > 0) {
            /*
             * If t is not empty, then disassembles the tree and sets it to the
             * correct trees and root variables.
             */
            BinaryTree<T> lhs = t.newInstance();
            BinaryTree<T> rhs = t.newInstance();

            T root = t.disassemble(lhs, rhs);

            /*
             * If the root equals x, then x is in the tree and answer is true.
             * Otherwise, if x is greater than the root, the statement
             * recursively adds in x in the right tree, or if x is less than the
             * root, the statement recursively adds in x in the left tree.
             */
            if (x.compareTo(root) > 0) {
                insertInTree(rhs, x);
            } else {
                insertInTree(lhs, x);
            }

            /*
             * Reassembles the original tree to preserve it.
             */
            t.assemble(root, lhs, rhs);
        } else {
            t.assemble(x, t.newInstance(), t.newInstance());
        }

    }

    /**
     * Removes and returns the smallest (left-most) label in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove the label
     * @return the smallest label in the given {@code BinaryTree}
     * @updates t
     * @requires IS_BST(t) and |t| > 0
     * @ensures <pre>
     * IS_BST(t)  and  removeSmallest = [the smallest label in #t]  and
     *  labels(t) = labels(#t) \ {removeSmallest}
     * </pre>
     */
    private static <T> T removeSmallest(BinaryTree<T> t) {
        assert t != null : "Violation of: t is not null";
        assert t.size() > 0 : "Violation of: |t| > 0";

        /*
         * Initializes answer as the root.
         */
        T answer = t.root();

        if (t.size() > 0) {
            /*
             * If t is not empty, then disassembles the tree and sets it to the
             * correct trees and root variables.
             */
            BinaryTree<T> lhs = t.newInstance();
            BinaryTree<T> rhs = t.newInstance();

            T root = t.disassemble(lhs, rhs);

            /*
             * Since smaller values than the root belong in the left tree, we
             * first check if it is not empty. If it is not, then we recursively
             * get the smallest value in the left tree and reassemble the tree.
             * The variable answer becomes the smallest value.
             */
            if (lhs.size() != 0) {
                answer = removeSmallest(lhs);
                t.assemble(root, lhs, rhs);
            } else {
                /*
                 * If a left tree doesn't exist, then the root becomes the right
                 * tree, and the answer stays as the root.
                 */
                t.transferFrom(rhs);
            }
        }
        return answer;

    }

    /**
     * Finds label {@code x} in {@code t}, removes it from {@code t}, and
     * returns it.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove label {@code x}
     * @param x
     *            the label to be removed
     * @return the removed label
     * @updates t
     * @requires IS_BST(t) and x is in labels(t)
     * @ensures <pre>
     * IS_BST(t)  and  removeFromTree = x  and
     *  labels(t) = labels(#t) \ {x}
     * </pre>
     */
    private static <T extends Comparable<T>> T removeFromTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";
        assert t.size() > 0 : "Violation of: x is in labels(t)";

        /*
         * The removed value is initialized as the root value.
         */
        T removed = t.root();

        if (t.size() > 0) {
            /*
             * If t is not empty, then disassembles the tree and sets it to the
             * correct trees and root variables.
             */
            BinaryTree<T> lhs = t.newInstance();
            BinaryTree<T> rhs = t.newInstance();

            T root = t.disassemble(lhs, rhs);

            if (x.compareTo(root) == 0 && rhs.size() != 0) {
                /*
                 * If the removed value is the root, then the tree is
                 * reassembled with the smallest value of the right hand tree
                 * becoming the new root, the old left tree being the new left
                 * tree, and the remaining right hand tree becoming the new
                 * right hand tree.
                 */
                t.assemble(removeSmallest(rhs), lhs, rhs);
            } else if (x.compareTo(root) == 0 && rhs.size() == 0) {
                /*
                 * If the removed value is the root and the right hand tree is
                 * empty, then the left hand tree becomes the new tree, with the
                 * root of the left hand tree becoming the root of the entire
                 * tree.
                 */
                t.transferFrom(lhs);
            } else if (x.compareTo(root) > 0) {
                /*
                 * If the removed value is greater than the root, then we
                 * recursively remove the value from the right hand tree.
                 */
                removed = removeFromTree(rhs, x);
            } else {
                /*
                 * If the removed value is less than the root, then we
                 * recursively remove the value from the right hand tree.
                 */
                removed = removeFromTree(lhs, x);
            }
            /*
             *
             * Reassembles the tree without the removed value.
             */

            t.assemble(root, lhs, rhs);
        }

        return removed;
    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {

        /*
         * Creates a representation which is a binary tree.
         */
        this.tree = new BinaryTree1<T>();

    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Set3a() {

        this.createNewRep();

    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @SuppressWarnings("unchecked")
    @Override
    public final Set<T> newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(Set<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Set3a<?> : ""
                + "Violation of: source is of dynamic type Set3<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Set3a<?>, and
         * the ? must be T or the call would not have compiled.
         */
        Set3a<T> localSource = (Set3a<T>) source;
        this.tree = localSource.tree;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void add(T x) {
        assert x != null : "Violation of: x is not null";
        assert !this.contains(x) : "Violation of: x is not in this";

        /*
         * Uses insertInTree to insert x into the right spot in the binary tree.
         */
        insertInTree(this.tree, x);

    }

    @Override
    public final T remove(T x) {
        assert x != null : "Violation of: x is not null";
        assert this.contains(x) : "Violation of: x is in this";

        /*
         * Removes x from the tree using removeFromTree and returning the
         * removed value.
         */
        return removeFromTree(this.tree, x);
    }

    @Override
    public final T removeAny() {
        assert this.size() > 0 : "Violation of: this /= empty_set";

        /*
         * Removes and returns the smallest value from the tree.
         */
        return removeSmallest(this.tree);
    }

    @Override
    public final boolean contains(T x) {
        assert x != null : "Violation of: x is not null";

        /*
         * Returns the boolean value returned when calling isInTree with the
         * tree variable and x.
         */
        return isInTree(this.tree, x);
    }

    @Override
    public final int size() {
        /*
         * Returns the size value returned from size method from kernel class.
         */
        return this.tree.size();
    }

    @Override
    public final Iterator<T> iterator() {
        /*
         * Returns the iterator of the tree representation.
         */
        return this.tree.iterator();
    }

}
