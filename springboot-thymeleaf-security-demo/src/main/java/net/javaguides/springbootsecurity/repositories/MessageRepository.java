package net.javaguides.springbootsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springbootsecurity.entities.Message;

/**
 * @author Pavan R
 *
 */
public interface MessageRepository extends JpaRepository<Message, Integer>{

}
