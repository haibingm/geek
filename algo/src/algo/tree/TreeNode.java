package algo.tree;

/**
 * @author bingg
 * @Date 2020-05-12
 * @Time 11:33
 * @Descrption 树结点
 */
public class TreeNode<T> {

	/**
	 * 数据
	 */
	private T data;

	/**
	 * 左子结点
	 */
	private TreeNode<T> left;

	/**
	 * 右子结点
	 */
	private TreeNode<T> right;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public TreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}

	public TreeNode<T> getRight() {
		return right;
	}

	public void setRight(TreeNode<T> right) {
		this.right = right;
	}

	public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public TreeNode<T> createNode(T data){
		return new TreeNode<T>(data, null, null);
	}
}
