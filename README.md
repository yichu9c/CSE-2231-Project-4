Project: Set on Binary Search Trees
Objectives
Familiarity with writing a kernel class for a new type and its kernel operations (Set layered on BinaryTree, using a binary search tree).
Familiarity with developing and using specification-based test plans.
The Problem
The problem is to complete and carefully test implementations of the constructor and all kernel methods defined in interface SetKernel, building the data structure representing a Set object by layering it on top of BinaryTree. The algorithmic approach is to use a binary search tree to reduce search time from linear (as in Set2) to logarithmic in the number of elements in the set; at least, there is this reduction in execution time "on average", though not in the worst case.

Setup
Only one member of the team should follow the steps to set up an Eclipse project for this assignment. The project should then be shared with the rest of the team by using the Subversion version control system as learned in the Version Control With Subversion lab.

To get started, import the project for this assignment, SetOnBST, from the SetOnBST.zip file available at this link. If you don't remember how to do this, see these Setup instructions from an earlier project.

Method
Complete the bodies of the createNewRep, isInTree, insertInTree, removeSmallest, and removeFromTree private methods, of the (no-argument) constructor, and of the kernel methods (add, remove, removeAny, contains, and size) in Set3a in the src folder.
Develop a complete and systematic test plan for the SetKernel constructor and kernel methods and add your test cases at the end of SetTest in the test folder. Note that you should have already developed such a test plan for an earlier homework and lab. Just make sure you improve it as necessary to meet the standards of quality and completeness that have been discussed before.
When you and your teammate are done with the project, decide who is going to submit your solution. That team member should select the Eclipse project SetOnBST (not just some of the files, but the whole project) containing the complete group submission, create a zip archive of it, and submit the zip archive to the Carmen dropbox for this project, as described in Submitting a Project. Note that you will only be allowed one submission per group, that is, your group can submit as many times as you want, but only the last submission will be on Carmen and will be graded. Under no circumstance will teammates be allowed to submit separate solutions. Make sure that you and your partner agree on what should be submitted.
Additional Activities
Here are some possible additional activities related to this project. Any extra work is strictly optional, for your own benefit, and will not directly affect your grade.

Record actual execution times of the public methods, comparing them to those the Set2 implementation (on Queue) you did in a lab and the Set4 implementation (with hashing) from the components.jar library. To time the execution time of a method call you can use the Stopwatch component you encountered in a CSE 2221 lab.
Find a balanced tree scheme, i.e., one that keeps the binary search tree balanced so its depth is always logarithmic in the number of pairs stored, and modify your code to incorporate it. (If you decide to try this, please contact your instructor first for some advice! The simplest way to do this involves using the inOrderAssemble method of BinaryTrees, which was mentioned but not discussed in class. This approach to balanced trees is not something you are likely to find by googling it.)
Record actual execution times of the public methods with a balanced tree scheme in place, comparing them to those without balancing and to those of the other implementations of Set.
