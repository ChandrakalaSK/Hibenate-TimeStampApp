package in.ineuron.Test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.Model.BankAccount;
import in.ineuron.Util.HibernateUtil;

public class InsertRecordApp {

	public static void main(String[] args) {
		
		Session session=null;
		Transaction transaction=null;
		boolean flag = false;
		Long id=null;
		try {
		session=HibernateUtil.getSession();
		
		if(session !=null)
		{
			transaction = session.beginTransaction();
			if(transaction != null)
			{
				BankAccount account=new BankAccount();
				account.setBalance(999999.5);
				account.setHolderName("sachin");
				account.setType("savings");
				
				id = (Long) session.save(account);
				
				flag = true;
			}
			
		}
		}catch (HibernateException e) {
			e.printStackTrace();
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			if(flag) {
				transaction.commit();
			System.out.println("Object inserted into to database id ::" + id);
			
			}
			else {
				transaction.rollback();
				System.out.println("Object not  inserted into to database");
			
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
