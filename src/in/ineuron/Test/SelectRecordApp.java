package in.ineuron.Test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.Model.BankAccount;
import in.ineuron.Util.HibernateUtil;

public class SelectRecordApp {
	
	public static void main(String[] args) throws Exception
	{
		
		Session session = null;
		
		BankAccount account = null;
		Long id = 1L;
		Transaction transaction = null;
		Boolean flag = false;
		
		try {
			session = HibernateUtil.getSession();
			 account = session.get(BankAccount.class, id);
			 System.out.println("Before modification :: "+account);
			 
			 if(account != null)
			 {
				 transaction =session.beginTransaction();
				account.setBalance(account.getBalance()+10000); 
				flag = true;
			 }else
			 {
				 System.out.println("Record not available for the given id :: "+id);
			 }
		} catch (HibernateException he) 
		{
			he.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(flag)
			{
				transaction.commit();
				System.out.println("Object updated");
				System.out.println("Account opening date    :: "+account.getOpeningDate());
				System.out.println("Account lastly modified :: "+account.getLastUpdated());
				System.out.println("Account version count   :: "+account.getCount());
			}
			else
			{
				transaction.rollback();
				System.out.println("Object not updated");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}
}
