package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 *      x^6
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		//float coeff= 0;
		//int deg = 0; 
		Node p1= poly1;
		Node p2=poly2;
		Node ans = null; //create a null pointer
		//Node ans = new Node(0, 0, null);
		//Node ptr=ans;
		
		while( p1!=null && p2 != null) { //while both of the polynomials are not null
			//in case one polynomial is larger than the other
			
			if((p1!=null) && (p2!=null)) {
				System.out.println(p1.term + " | " + p2.term);
				if(p1.term.degree == p2.term.degree) { //if polynomial degrees are equal
					int deg = p1.term.degree; 
					float coeff = (p1.term.coeff + p2.term.coeff);
					if(coeff!=0) {
						ans = new Node(coeff, deg, ans); //create a node with the result
					}
					p1=p1.next; //move the pointer
					p2=p2.next; //move the pointer
				} else if(p1.term.degree < p2.term.degree) { //if poly2 degree is larger 
					float coeff= p1.term.coeff; //since you want it to be in accending order at this point put the smaller degree first
					int deg =p1.term.degree;
					ans = new Node(coeff, deg, ans);
					p1=p1.next;
				} else if(p2.term.degree < p1.term.degree) { 
					float coeff= p2.term.coeff;
					int deg =p2.term.degree;
					ans = new Node(coeff, deg, ans);
					p2=p2.next;
				}
			}
			
		}
		while (p1 != null) { 
			ans = new Node(p1.term.coeff, p1.term.degree, ans);
			p1 = p1.next;
		}
		while (p2 != null) {
			ans = new Node(p2.term.coeff, p2.term.degree, ans);
			p2 = p2.next;
		}
		Node thing = null;
		Node ptr = ans;
		while(ptr!=null) {
			thing = new Node(ptr.term.coeff, ptr.term.degree, thing);
			ptr = ptr.next;
		}
		return thing;
	}
			
			/*Node temp= ans;
			
		    while(temp!=null) {
		    	temp= temp.next;
		    	temp.next= ans;
		    	ans = temp;
		    }
		    ans = new Node(coeff, deg, null);
		    
		    return ans;
		 */
		
		    
		//}
		
				    
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION 
		//return ans;
		
		
	
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		
		Node answer=null;
		//Node ptr1=poly1;
		Node ptr2=poly2;
		
		
		if(poly1==null||poly2==null) {
			return null;
		}
		
		float coeff=0;
		int degree=0;
		int maximum =0;
		
		for(Node ptr1=poly1;ptr1!=null;ptr1=ptr1.next) {
			while(ptr2!=null) {
				coeff = ptr1.term.coeff*ptr2.term.coeff;//Multiply the coeff
				degree=ptr1.term.degree+ptr2.term.degree; //add the degrees
				answer=new Node(coeff, degree, answer);
					if(maximum < degree) {
						maximum = degree;
					}
					ptr2=ptr2.next;
			}
			//ptr1=ptr1.next;
			ptr2=poly2;//you need to make ptr start from the beginning
		}
		Node product=null;
		
		for(int i=0; i<=maximum; i++) {//going through term degrees
		Node temp=answer;
		float sum=0;
			while(temp!=null) {
				if(i==temp.term.degree) {
					sum+=temp.term.coeff;
					//temp=temp.next;
					//temp=new Node(coeff, degree, temp);
				}	
					temp=temp.next;
				}
			if(sum!=0) {
			product=new Node(sum, i, product);
			}
		}
			
		Node reverse = null;
		Node point = product;
		while(point!=null) {
			reverse = new Node(point.term.coeff, point.term.degree, reverse);
			point = point.next;
		//return answer;
	}
		return reverse;
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		float ans = 0; 
		for(Node current = poly; current !=null; current= current.next) {
			ans = (float)(ans + current.term.coeff*Math.pow(x,current.term.degree));	
		
	}
		return ans;
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}