package com.javarush.todolist.dao.impl;

import com.javarush.todolist.dao.TaskDAO;
import com.javarush.todolist.domain.Task;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TaskDAOImpl implements TaskDAO {

    private final SessionFactory sessionFactory;

    public TaskDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void create(Task task) {
        sessionFactory.getCurrentSession().persist(task);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(Task task) {
        sessionFactory.getCurrentSession().merge(task);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Task task) {
        sessionFactory.getCurrentSession().remove(task);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Task getById(int id) {
        return (Task) sessionFactory.getCurrentSession().get(Task.class, id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Task> getAll(int offset, int count) {
        Query<Task> query = sessionFactory.getCurrentSession().createQuery("from Task", Task.class);
        query.setMaxResults(count);
        query.setFirstResult(offset);
        return query.list();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public int getCount() {
        Query<Long> query = sessionFactory.getCurrentSession().createQuery("select count(*) from Task", Long.class);
        return Math.toIntExact(query.uniqueResult());
    }
}
