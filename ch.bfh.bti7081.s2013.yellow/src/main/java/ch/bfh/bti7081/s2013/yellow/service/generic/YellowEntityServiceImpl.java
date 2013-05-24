package ch.bfh.bti7081.s2013.yellow.service.generic;

import ch.bfh.bti7081.s2013.yellow.dao.generic.YellowEntityDAO;
import ch.bfh.bti7081.s2013.yellow.model.generic.YellowEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * @author Andy Pollari
 * Implementation of the YellowEntityService
 */
@Transactional
@Service
public class YellowEntityServiceImpl extends GenericServiceImpl<YellowEntity> implements YellowEntityService {

    @Autowired
    YellowEntityDAO yellowEntityDAO;

    @PostConstruct
    public void init() {
        setDAO(yellowEntityDAO);
    }

}
